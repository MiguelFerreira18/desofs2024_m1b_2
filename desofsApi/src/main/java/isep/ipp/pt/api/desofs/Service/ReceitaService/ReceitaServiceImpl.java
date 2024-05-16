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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        TipoReceita tipoReceita = tipoReceitaRepo.findbyId(receitaService.getTipoReceita());
        Pacote pacote = pacoteRepo.findbyId(receitaService.getPacote());
        ReceitaSaveDTOService receitaSaveDTOService = new ReceitaSaveDTOService(receitaService.getPath(), receitaService.getNome(), pacote, tipoReceita);
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