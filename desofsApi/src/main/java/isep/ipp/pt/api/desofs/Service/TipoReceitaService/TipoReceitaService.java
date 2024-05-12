package isep.ipp.pt.api.desofs.Service.TipoReceitaService;

import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceResponse;

import java.util.List;

public interface TipoReceitaService {
    TipoReceitaDTOServiceResponse save(TipoReceitaDTOServiceRequest tipoReceita);
    TipoReceitaDTOServiceResponse findbyId(Long id);

    List<TipoReceitaDTOServiceResponse> findAll();

    void deleteAll();

    void deleteById(Long id);
}
