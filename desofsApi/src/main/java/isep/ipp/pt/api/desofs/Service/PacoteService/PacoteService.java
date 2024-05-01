package isep.ipp.pt.api.desofs.Service.PacoteService;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Model.Pacote;

import java.util.List;

public interface PacoteService {
    PacoteDTOServiceResponse save(PacoteDTOServiceRequest pacoteService);
    PacoteDTOServiceResponse findbyId(Long id);
    void disable(Long id);
    void enable(Long id);
    List<PacoteDTOServiceResponse> findAll();
    void deleteAll();
}
