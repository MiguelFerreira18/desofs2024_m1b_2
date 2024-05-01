package isep.ipp.pt.api.desofs.Mapper.PacoteMapper;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteSaveDTOService;
import isep.ipp.pt.api.desofs.Model.Pacote;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PacoteMapper {

    //PacoteMapper INSTANCE = Mappers.getMapper(PacoteMapper.class);
    //Controller Layr
    PacoteDTOServiceRequest toPacoteDtoServiceRequestFromPacoteDtoSaveRequest(PacoteDTOSaveRequest pacoteDTOSaveRequest);
    PacoteDTOResponse fromPacoteToDto(PacoteDTOServiceResponse pacote);


    //Service Layer
    PacoteDTOServiceRequest ToPacoteDtoServiceRequestFromPacoteDtoResponse(PacoteDTOResponse pacoteDTOResponse);
    PacoteDTOServiceResponse toPacoteDTOServiceResponseFromPacote(Pacote pacote);
    Pacote toPacotefromPacoteSaveDtoService(PacoteSaveDTOService pacoteDTOServiceRequest);
    List<PacoteDTOServiceResponse> toPacoteDTOServiceResponseListFromPacoteList(List<Pacote> all);


}
