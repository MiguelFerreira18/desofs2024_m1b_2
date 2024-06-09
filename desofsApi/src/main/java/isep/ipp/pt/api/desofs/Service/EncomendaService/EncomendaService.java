package isep.ipp.pt.api.desofs.Service.EncomendaService;

import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServiceResponse;
import java.util.List;

public interface EncomendaService {
    EncomendaDTOServiceResponse save(EncomendaDTOServiceRequest encomendaDTOServiceRequest);
    EncomendaDTOServiceResponse findbyId(Long encomendaId);

    EncomendaDTOServiceResponse update(EncomendaDTOServicePatchRequest encomendaDTOServicePatchRequest);
    List<EncomendaDTOServiceResponse> findAll(String userId);

    List<EncomendaDTOServiceResponse> findEncHistory(String userId);
    void deleteById(Long encomendaId);

    void deleteAll();
}
