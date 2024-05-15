package isep.ipp.pt.api.desofs.Mapper.TipoReceitaMapper;

import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer.TipoReceitaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer.TipoReceitaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TipoReceitaMapper {
    //Controller Layer
    TipoReceitaDTOServiceRequest toTipoReceitaDTOServiceRequestFromTipoReceitaDTOSaveRequest(TipoReceitaDTOSaveRequest tipoReceitaRequest);
    TipoReceitaDTOResponse toTipoReceitaDTOResponseFromTipoReceitaDTOServiceResponse(TipoReceitaDTOServiceResponse save);
    List<TipoReceitaDTOResponse> toTipoReceitaDTOResponseListFromTipoReceitaDTOServiceResponseList(List<TipoReceitaDTOServiceResponse> tipoReceitaServiceResponseList);

    //Service Layer
    TipoReceita toTipoReceitaFromTipoReceitaDTOServiceRequest(TipoReceitaDTOServiceRequest tipoReceita);
    TipoReceitaDTOServiceResponse toTipoReceitaDTOServiceResponseFromTipoReceita(TipoReceita save);
    List<TipoReceitaDTOServiceResponse> toTipoReceitaDTOServiceResponseListFromTipoReceita(List<TipoReceita> all);
}
