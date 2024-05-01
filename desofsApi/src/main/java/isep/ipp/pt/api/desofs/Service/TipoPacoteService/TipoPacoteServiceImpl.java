package isep.ipp.pt.api.desofs.Service.TipoPacoteService;

import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Mapper.TipoPacoteMapper.TipoPacoteMapper;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPacoteServiceImpl implements  TipoPacoteService {

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteServiceRepo;
    @Autowired
    private TipoPacoteMapper tipoPacoteMapper;



    @Override
    public TipoPacoteDTOServiceResponse save(TipoPacoteDTOServiceRequest tipoPacote) {
        TipoPacote tipoPacoteMapped = tipoPacoteMapper.toTipoPacoteFromTipoPacoteDTOServiceRequest(tipoPacote);
        return tipoPacoteMapper.toTipoPacoteDTOServiceResponseFromTipoPacote(tipoPacoteServiceRepo.save(tipoPacoteMapped));
    }

    @Override
    public TipoPacoteDTOServiceResponse findbyId(Long id) {
        return tipoPacoteMapper.toTipoPacoteDTOServiceResponseFromTipoPacote(tipoPacoteServiceRepo.findbyId(id));
    }

    @Override
    public List<TipoPacoteDTOServiceResponse> findAll() {
        return tipoPacoteMapper.toTipoPacoteDTOServiceResponseListFromTipoPacote(tipoPacoteServiceRepo.findAll());
    }

    @Override
    public void deleteAll() {
        tipoPacoteServiceRepo.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        tipoPacoteServiceRepo.deleteById(id);
    }
}
