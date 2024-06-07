package isep.ipp.pt.api.desofs.Service.ReceitaService;

import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.*;
import isep.ipp.pt.api.desofs.Mapper.PacoteMapper.PacoteMapper;
import isep.ipp.pt.api.desofs.Mapper.ReceitaMapper.ReceitaMapper;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Utils.PersonalValidation;
import org.apache.tika.exception.TikaException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.tika.Tika;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

@Service
public class ReceitaServiceImpl implements ReceitaService{

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaRepo;

    @Autowired
    private PacoteServiceRepo pacoteRepo;

    @Autowired
    private ReceitaServiceRepo receitaRepo;

    @Autowired
    private ReceitaMapper receitaMapper;

    @Autowired
    private PersonalValidation validation;

    private final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private final Tika tika = new Tika();

    @Override
    public ReceitaDTOServiceResponse save(ReceitaDTOServiceRequest receitaService) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        String formattedDate = dateFormat.format(date);

        String outputPath = "./Recipes/" + formattedDate + ".pdf";
        generateFile(receitaService.getPath(), outputPath);

        TipoReceita tipoReceita = tipoReceitaRepo.findbyId(receitaService.getTipoReceita());
        Pacote pacote = pacoteRepo.findbyId(receitaService.getPacote());
        ReceitaSaveDTOService receitaSaveDTOService = new ReceitaSaveDTOService(outputPath, receitaService.getNome(), pacote, tipoReceita);
        if (!validation.validate(receitaSaveDTOService)) {
            return null;
        }
        return receitaMapper.toReceitaDTOServiceResponseFromReceita(receitaRepo.save(receitaMapper.toReceitafromReceitaSaveDtoService(receitaSaveDTOService)));
    }

    @Override
    public ReceitaDTOServiceResponse findbyId(Long id) {
        return receitaMapper.toReceitaDTOServiceResponseFromReceita(receitaRepo.findbyId(id));
    }

    @Override
    public ReceitaDTOServiceResponse update(ReceitaDTOServicePatchRequest receitaRequestService) {
        TipoReceita tipoReceita = tipoReceitaRepo.findbyId(receitaRequestService.getTipoReceita());
        Pacote pacote = pacoteRepo.findbyId(receitaRequestService.getPacote());
        ReceitaPatchDTOService receitaPatchDTOService = new ReceitaPatchDTOService(receitaRequestService.getReceitaId(), receitaRequestService.getPath(), receitaRequestService.getNome(), pacote, tipoReceita);
        if (!validation.validate(receitaPatchDTOService)) {
            return null;
        }
        return receitaMapper.toReceitaDTOServiceResponseFromReceita(receitaRepo.save(receitaMapper.toReceitafromReceitaPatchDtoService(receitaPatchDTOService)));
    }

    @Override
    public List<ReceitaDTOServiceResponse> findAll() {
        return receitaMapper.toReceitaDTOServiceResponseListFromReceitaList(receitaRepo.findAll());
    }

    private boolean validateFile(File file) {
        try {
            String fileType = tika.detect(file);
            if (!fileType.equals(MimeTypes.OCTET_STREAM) && !fileType.equals("application/pdf")) {
                System.out.println("Invalid file type: " + fileType);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (file.length() > MAX_FILE_SIZE) {
            System.out.println("File size exceeds the maximum allowed limit.");
            return false;
        }
        return true;
    }

    @Override
    public void generateFile(String path , String outputPath) {
        if(path == null || outputPath == null){
            return;
        }
        File file = new File(path);
        if(!validateFile(file)){
            return;
        }

        try {
            Files.copy(file.toPath(), Paths.get(outputPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File downloaded to: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        receitaRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        receitaRepo.deleteAll();
    }
}
