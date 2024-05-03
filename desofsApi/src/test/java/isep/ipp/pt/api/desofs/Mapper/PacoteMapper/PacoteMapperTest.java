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
    void toPacoteDtoServiceRequestFromPacoteDtoSaveRequest() {
        TipoPacote tipoPacote = new TipoPacote(1L, "tipoPacote");
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest("nome", 10.0, "description", false, null);

        assertEquals(pacoteMapper.toPacoteDtoServiceRequestFromPacoteDtoSaveRequest(pacoteDTOSaveRequest).getNome(), pacoteDTOSaveRequest.getNome());
        assertEquals(pacoteMapper.toPacoteDtoServiceRequestFromPacoteDtoSaveRequest(pacoteDTOSaveRequest).getPacoteBasePrice(), pacoteDTOSaveRequest.getPacoteBasePrice());
        assertEquals(pacoteMapper.toPacoteDtoServiceRequestFromPacoteDtoSaveRequest(pacoteDTOSaveRequest).getPacoteDescription(), pacoteDTOSaveRequest.getPacoteDescription());
        assertEquals(pacoteMapper.toPacoteDtoServiceRequestFromPacoteDtoSaveRequest(pacoteDTOSaveRequest).getDisabled(), pacoteDTOSaveRequest.getDisabled());
        assertNull(pacoteMapper.toPacoteDtoServiceRequestFromPacoteDtoSaveRequest(pacoteDTOSaveRequest).getTipoPacote());


    }
}