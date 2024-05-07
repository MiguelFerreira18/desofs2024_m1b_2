package isep.ipp.pt.api.desofs.Service.TipoPacoteService;

import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceResponse;

import java.util.List;

public interface TipoPacoteService {

    TipoPacoteDTOServiceResponse save(TipoPacoteDTOServiceRequest tipoPacote);
    TipoPacoteDTOServiceResponse findbyId(Long id);

    List<TipoPacoteDTOServiceResponse> findAll();

    void deleteAll();

    void deleteById(Long id);

}
