package isep.ipp.pt.api.desofs.Mapper.TipoPacoteMapper;

import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer.TipoPacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer.TipoPacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TipoPacoteMapper {


    //Controller Layer
    TipoPacoteDTOServiceRequest toTipoPacoteDTOServiceRequestFromTipoPacoteDTOSaveRequest(TipoPacoteDTOSaveRequest tipoPacoteRequest);
    TipoPacoteDTOResponse toTipoPacoteDTOResponseFromTipoPacoteDTOServiceResponse(TipoPacoteDTOServiceResponse save);
    List<TipoPacoteDTOResponse> toTipoPacoteDTOResponseListFromTipoPacoteDTOServiceResponseList(List<TipoPacoteDTOServiceResponse> tipoPacoteServiceResponseList);


    //Service Layer
    TipoPacote toTipoPacoteFromTipoPacoteDTOServiceRequest(TipoPacoteDTOServiceRequest tipoPacote);
    TipoPacoteDTOServiceResponse toTipoPacoteDTOServiceResponseFromTipoPacote(TipoPacote save);
    List<TipoPacoteDTOServiceResponse> toTipoPacoteDTOServiceResponseListFromTipoPacote(List<TipoPacote> all);

}
