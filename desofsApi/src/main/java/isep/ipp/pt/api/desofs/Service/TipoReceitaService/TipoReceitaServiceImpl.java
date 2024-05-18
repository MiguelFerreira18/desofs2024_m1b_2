package isep.ipp.pt.api.desofs.Service.TipoReceitaService;

import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Mapper.TipoReceitaMapper.TipoReceitaMapper;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Utils.PersonalValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoReceitaServiceImpl implements TipoReceitaService{

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaServiceRepo;
    @Autowired
    private TipoReceitaMapper tipoReceitaMapper;
    @Autowired
    private PersonalValidation validation;

    @Override
    public TipoReceitaDTOServiceResponse save(TipoReceitaDTOServiceRequest tipoReceita) {
        if (!validation.validate(tipoReceita)) {
            return null;
        }
        TipoReceita tipoReceitaMapped = tipoReceitaMapper.toTipoReceitaFromTipoReceitaDTOServiceRequest(tipoReceita);
        return tipoReceitaMapper.toTipoReceitaDTOServiceResponseFromTipoReceita(tipoReceitaServiceRepo.save(tipoReceitaMapped));
    }

    @Override
    public TipoReceitaDTOServiceResponse findbyId(Long id) {
        return tipoReceitaMapper.toTipoReceitaDTOServiceResponseFromTipoReceita(tipoReceitaServiceRepo.findbyId(id));
    }

    @Override
    public List<TipoReceitaDTOServiceResponse> findAll() {
        return tipoReceitaMapper.toTipoReceitaDTOServiceResponseListFromTipoReceita(tipoReceitaServiceRepo.findAll());
    }

    @Override
    public void deleteAll() {
        tipoReceitaServiceRepo.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        tipoReceitaServiceRepo.deleteById(id);
    }
}
