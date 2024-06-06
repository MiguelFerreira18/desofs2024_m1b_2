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
    @Override
    public ReceitaDTOServiceResponse save(ReceitaDTOServiceRequest receitaService) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        String formattedDate = dateFormat.format(date);

        Tika tika = new Tika();
        System.out.println(receitaService.getPath());
        File file = new File(receitaService.getPath());
        String fileType;
        try {
            fileType = tika.detect(file);
            if (!fileType.equals(MimeTypes.OCTET_STREAM) && !fileType.equals("application/pdf")) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String outputPath = "./Recipes/" + formattedDate + ".pdf";
        System.out.println(outputPath);
        try {
            Files.copy(file.toPath(), Paths.get(outputPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // Handle exception accordingly
            e.printStackTrace();
            return null;
        }

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

    @Override
    public void deleteById(Long id) {
        receitaRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        receitaRepo.deleteAll();
    }
}
