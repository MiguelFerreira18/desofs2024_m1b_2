package isep.ipp.pt.api.desofs.Mapper.PacoteMapper;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.Pacote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PacoteMapper {

    //PacoteMapper INSTANCE = Mappers.getMapper(PacoteMapper.class);

    Pacote toPacoteFromSaveDTO(PacoteDTOSaveRequest pacoteDTOSaveRequest);
    PacoteDTOResponse fromPacoteToDto(Pacote pacote);
    

}
