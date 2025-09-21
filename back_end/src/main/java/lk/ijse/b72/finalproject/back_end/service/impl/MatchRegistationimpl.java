package lk.ijse.b72.finalproject.back_end.service.impl;

import lk.ijse.b72.finalproject.back_end.DTO.MatchRegisterDTO;
import lk.ijse.b72.finalproject.back_end.Entity.MatchRegistation;
import lk.ijse.b72.finalproject.back_end.repo.MatchRegistrationRepo;
import lk.ijse.b72.finalproject.back_end.service.MatchRegistationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchRegistationimpl implements MatchRegistationService {


    @Autowired
    private MatchRegistrationRepo matchRegistrationRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void save(MatchRegisterDTO matchRegisterDTO) {


        if (matchRegistrationRepo.existsById(matchRegisterDTO.getId())) {
            throw new RuntimeException("Fill Hall Already exists");
        }
        matchRegistrationRepo.save(modelMapper.map(matchRegisterDTO, MatchRegistation.class));

    }


    @Override
    public ArrayList<Map<String, Object>> getAll() {
        List<MatchRegistation> matchregistation = matchRegistrationRepo.findAll();
        ArrayList<Map<String, Object>> result = new ArrayList<>();

        for (MatchRegistation registration : matchregistation) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", registration.getId());
            item.put("hallName", registration.getStadium().getName());
            item.put("filmTitle", registration.getMatch().getTitle());
            item.put("timeDescription", registration.getTimeTable().getDescription());

            result.add(item);
        }

        return result;
    }


    public void delete(Long id) {
        if (matchRegistrationRepo.existsById(id)) {
            matchRegistrationRepo.deleteFilmRegistrationById(id);
        } else {
            throw new RuntimeException("Film registration does not exist");
        }


    }


    @Override
    public void update(MatchRegisterDTO matchRegisterDTO) {
        if (matchRegistrationRepo.existsById(matchRegisterDTO.getId())) {
            matchRegistrationRepo.save(modelMapper.map(matchRegisterDTO, MatchRegistation.class));
        }
    }
}

