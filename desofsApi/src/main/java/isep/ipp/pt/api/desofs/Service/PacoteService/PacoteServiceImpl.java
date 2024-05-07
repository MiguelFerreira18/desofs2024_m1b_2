package isep.ipp.pt.api.desofs.Service.PacoteService;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacotePatchDTOService;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteSaveDTOService;
import isep.ipp.pt.api.desofs.Mapper.PacoteMapper.PacoteMapper;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacoteServiceImpl implements PacoteService {

    @Autowired
    private PacoteServiceRepo pacoteRepo;
    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;
    @Autowired
    private PacoteMapper pacoteMapper;

    @Override
    public PacoteDTOServiceResponse save(PacoteDTOServiceRequest pacoteService) {
        TipoPacote tipoPacote = tipoPacoteRepo.findbyId(pacoteService.getTipoPacote());
        PacoteSaveDTOService pacoteSaveDTOService = new PacoteSaveDTOService(pacoteService.getNome(), pacoteService.getPacoteBasePrice(), pacoteService.getPacoteDescription(), pacoteService.getDisabled(), tipoPacote);
        return pacoteMapper.toPacoteDTOServiceResponseFromPacote(pacoteRepo.save(pacoteMapper.toPacotefromPacoteSaveDtoService(pacoteSaveDTOService)));
    }

    @Override
    public PacoteDTOServiceResponse findbyId(Long id) {
        return pacoteMapper.toPacoteDTOServiceResponseFromPacote(pacoteRepo.findbyId(id));
    }

    @Override
    public PacoteDTOServiceResponse update(PacoteDTOServicePatchRequest pacoteRequestService) {
        TipoPacote tipoPacote = tipoPacoteRepo.findbyId(pacoteRequestService.getTipoPacote());
        PacotePatchDTOService pacotePatchDTOService = new PacotePatchDTOService(pacoteRequestService.getPacoteId() ,pacoteRequestService.getNome(), pacoteRequestService.getPacoteBasePrice(), pacoteRequestService.getPacoteDescription(), pacoteRequestService.getDisabled(), tipoPacote);
        return pacoteMapper.toPacoteDTOServiceResponseFromPacote(pacoteRepo.save(pacoteMapper.toPacotefromPacotePatchDtoService(pacotePatchDTOService)));

    }

    @Override
    public void disable(Long id) {
        pacoteRepo.disable(id);

    }

    @Override
    public void enable(Long id) {
        pacoteRepo.enable(id);
    }

    @Override
    public List<PacoteDTOServiceResponse> findAll() {
        return pacoteMapper.toPacoteDTOServiceResponseListFromPacoteList(pacoteRepo.findAll());
    }

    @Override
    public void deleteById(Long id) {
        pacoteRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        pacoteRepo.deleteAll();
    }
}
