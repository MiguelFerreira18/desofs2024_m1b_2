package isep.ipp.pt.api.desofs.Service.ReceitaService;

import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.ReceitaDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.ReceitaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.ReceitaDTOServiceResponse;

import java.util.List;

public interface ReceitaService {
    ReceitaDTOServiceResponse save(ReceitaDTOServiceRequest receitaService);
    ReceitaDTOServiceResponse findbyId(Long id);
    ReceitaDTOServiceResponse update(ReceitaDTOServicePatchRequest receitaRequestService);
    List<ReceitaDTOServiceResponse> findAll();
    void deleteById(Long id);
    void deleteAll();
}
