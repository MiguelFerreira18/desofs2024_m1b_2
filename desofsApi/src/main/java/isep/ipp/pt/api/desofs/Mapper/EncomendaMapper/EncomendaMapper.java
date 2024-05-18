package isep.ipp.pt.api.desofs.Mapper.EncomendaMapper;


import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOPatchRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.*;
import isep.ipp.pt.api.desofs.Model.Encomenda;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface EncomendaMapper {
    //Controller Layer
    EncomendaDTOServiceRequest toEncomendaDtoServiceRequestFromEncomendaDtoSaveRequest(EncomendaDTOSaveRequest encomendaDTOSaveRequest);
    EncomendaDTOResponse fromEncomendaToDto(EncomendaDTOServiceResponse encomenda);
    List<EncomendaDTOResponse> fromEncomendaDtoServiceResponseListToEncomendaDToResponseList(List<EncomendaDTOServiceResponse> all);
    EncomendaDTOServicePatchRequest toEncomendaDTOServicePatchRequestFromEncomendaDTOPatchRequest(EncomendaDTOPatchRequest encomenda);

    //Service Layer
    EncomendaDTOServiceResponse toEncomendaDTOServiceResponseFromEncomenda(Encomenda encomenda);
    Encomenda toEncomendafromEncomendaSaveDtoService(EncomendaSaveDTOService encomendaDTOServiceRequest);
    List<EncomendaDTOServiceResponse> toEncomendaDTOServiceResponseListFromEncomendaList(List<Encomenda> all);
    Encomenda toEncomendafromEncomendaPatchDtoService(EncomendaPatchDTOService encomendaPatchDTOService);
}
