package isep.ipp.pt.api.desofs.Mapper.ReceitaMapper;

import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOPatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.*;
import isep.ipp.pt.api.desofs.Model.Receita;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ReceitaMapper {

    //Controller Layer
    ReceitaDTOServiceRequest toReceitaDtoServiceRequestFromReceitaDtoSaveRequest(ReceitaDTOSaveRequest receitaDTOSaveRequest);
    ReceitaDTOResponse fromReceitaToDto(ReceitaDTOServiceResponse receita);
    List<ReceitaDTOResponse> fromReceitaDtoServiceResponseListToReceitaDToResponseList(List<ReceitaDTOServiceResponse> all);
    ReceitaDTOServicePatchRequest toReceitaDTOServicePAtchRequestFromReceitaDTOPatchRequest(ReceitaDTOPatchRequest receita);

    //Service Layer
    ReceitaDTOServiceResponse toReceitaDTOServiceResponseFromReceita(Receita receita);
    Receita toReceitafromReceitaSaveDtoService(ReceitaSaveDTOService receitaDTOServiceRequest);
    List<ReceitaDTOServiceResponse> toReceitaDTOServiceResponseListFromReceitaList(List<Receita> all);
    Receita toReceitafromReceitaPatchDtoService(ReceitaPatchDTOService receitaPatchDTOService);
}
