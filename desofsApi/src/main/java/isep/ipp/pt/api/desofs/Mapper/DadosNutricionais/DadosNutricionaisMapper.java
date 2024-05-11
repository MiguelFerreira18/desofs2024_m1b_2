package isep.ipp.pt.api.desofs.Mapper.DadosNutricionais;

import isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer.DadosNutricionaisDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer.DadosNutricionaisDtoResponse;
import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DadosNutricionaisMapper {
    DadosNutricionaisDtoResponse fromDadosNutricionaisToDadosNutricionaisDtoResponse(DadosNutricionais dadosNutricionais);

    DadosNutricionais fromDadosNutricionaisDtoResponseToDadosNutricionaisDTO(DadosNutricionaisDTOSaveRequest dadosNutricionaisDTOSaveRequest);

    List<DadosNutricionaisDtoResponse> fromDadosNutricionaisListToDadosNutricionaisDtoResponseList(List<DadosNutricionais> all);

//    DadosNutricionais fromDadosNutricionaisSaveResquestToDadosNutricionais(DadosNutricionaisSaveRequest DadosNutricionaisSaveRequest);
}
