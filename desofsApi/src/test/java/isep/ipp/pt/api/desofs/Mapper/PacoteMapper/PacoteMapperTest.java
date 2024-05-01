package isep.ipp.pt.api.desofs.Mapper.PacoteMapper;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacoteMapperTest {

    @Autowired
    PacoteMapper pacoteMapper;
    @Test
    void toPacoteFromSaveDTO() {
        TipoPacote tipoPacote = new TipoPacote(1L, "tipoPacote");
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest("nome", 10.0, "description", false, null);

        assertEquals(pacoteMapper.toPacoteFromSaveDTO(pacoteDTOSaveRequest).getNome(), pacoteDTOSaveRequest.getNome());
        assertEquals(pacoteMapper.toPacoteFromSaveDTO(pacoteDTOSaveRequest).getPacoteBasePrice(), pacoteDTOSaveRequest.getPacoteBasePrice());
        assertEquals(pacoteMapper.toPacoteFromSaveDTO(pacoteDTOSaveRequest).getPacoteDescription(), pacoteDTOSaveRequest.getPacoteDescription());
        assertEquals(pacoteMapper.toPacoteFromSaveDTO(pacoteDTOSaveRequest).isDisabled(), pacoteDTOSaveRequest.isDisabled());
        assertNull(pacoteMapper.toPacoteFromSaveDTO(pacoteDTOSaveRequest).getTipoPacote());

    }
}