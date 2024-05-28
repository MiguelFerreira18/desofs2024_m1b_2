package isep.ipp.pt.api.desofs.Service.EncomendaService;

import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.*;
import isep.ipp.pt.api.desofs.Mapper.EncomendaMapper.EncomendaMapper;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.EncomendaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncomendaServiceImpl implements EncomendaService{
    @Autowired
    private EncomendaServiceRepo encomendaServiceRepo;
    @Autowired
    private EncomendaMapper encomendaMapper;
    @Autowired
    private PacoteServiceRepo pacoteRepo;
    @Autowired
    private UserServiceRepo userRepo;

    @Override
    public EncomendaDTOServiceResponse save(@Valid EncomendaDTOServiceRequest encomendaDTOServiceRequest) {
        Pacote pacote = pacoteRepo.findbyId(encomendaDTOServiceRequest.getPacoteId());
        User user = userRepo.getUserById(encomendaDTOServiceRequest.getUserId());
        EncomendaSaveDTOService encomendaSaveDTOService = new EncomendaSaveDTOService(encomendaDTOServiceRequest.getMealsPerWeek(), encomendaDTOServiceRequest.getNumberOfPeople(), encomendaDTOServiceRequest.getPrice(), encomendaDTOServiceRequest.getDataEncomenda(), pacote, encomendaDTOServiceRequest.getEstado(), user);
        return encomendaMapper.toEncomendaDTOServiceResponseFromEncomenda(encomendaServiceRepo.save(encomendaMapper.toEncomendafromEncomendaSaveDtoService(encomendaSaveDTOService)));
    }

    @Override
    public EncomendaDTOServiceResponse findbyId(Long encomendaId) {
        return encomendaMapper.toEncomendaDTOServiceResponseFromEncomenda(encomendaServiceRepo.findbyId(encomendaId));
    }

    @Override
    public EncomendaDTOServiceResponse update(@Valid EncomendaDTOServicePatchRequest encomendaDTOServicePatchRequest) {
        Pacote pacote = pacoteRepo.findbyId(encomendaDTOServicePatchRequest.getPacoteId());
        User user = userRepo.getUserById(encomendaDTOServicePatchRequest.getUserId());
        EncomendaPatchDTOService encomendaPatchDTOService = new EncomendaPatchDTOService(encomendaDTOServicePatchRequest.getEncomendaId(),encomendaDTOServicePatchRequest.getMealsPerWeek(), encomendaDTOServicePatchRequest.getNumberOfPeople(), encomendaDTOServicePatchRequest.getPrice(), encomendaDTOServicePatchRequest.getDataEncomenda(), pacote, encomendaDTOServicePatchRequest.getEstado(), user);
        return encomendaMapper.toEncomendaDTOServiceResponseFromEncomenda(encomendaServiceRepo.save(encomendaMapper.toEncomendafromEncomendaPatchDtoService(encomendaPatchDTOService)));
    }

    @Override
    public List<EncomendaDTOServiceResponse> findAll(Long userId) {
        return encomendaMapper.toEncomendaDTOServiceResponseListFromEncomendaList(encomendaServiceRepo.findAll(userId));
    }

    @Override
    public List<EncomendaDTOServiceResponse> findEncHistory(Long userId) {
        return encomendaMapper.toEncomendaDTOServiceResponseListFromEncomendaList(encomendaServiceRepo.findEncHistory(userId));
    }

    @Override
    public void deleteById(Long encomendaId) {
        encomendaServiceRepo.deleteById(encomendaId);
    }

    @Override
    public void deleteAll() {
        encomendaServiceRepo.deleteAll();
    }
}
