package isep.ipp.pt.api.desofs.Mapper.DadosNutricionais;

import isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer.DadosNutricionaisDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer.DadosNutricionaisDtoResponse;
import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DadosNutricionaisMapper {
    DadosNutricionaisDtoResponse fromDadosNutricionaisToDadosNutricionaisDtoResponse(DadosNutricionais dadosNutricionais);

    DadosNutricionais fromDadosNutricionaisDtoResponseToDadosNutricionaisDTO(DadosNutricionaisDTOSaveRequest dadosNutricionaisDTOSaveRequest);

//    DadosNutricionais fromDadosNutricionaisSaveResquestToDadosNutricionais(DadosNutricionaisSaveRequest DadosNutricionaisSaveRequest);
}
