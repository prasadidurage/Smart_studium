package lk.ijse.b72.finalproject.back_end.service.impl;

import lk.ijse.b72.finalproject.back_end.DTO.TimeTableDTO;
import lk.ijse.b72.finalproject.back_end.Entity.TimeTable;
import lk.ijse.b72.finalproject.back_end.repo.MatchRegistrationRepo;
import lk.ijse.b72.finalproject.back_end.repo.TimeTableRepo;
import lk.ijse.b72.finalproject.back_end.service.TimeTableService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {


    @Autowired
    private MatchRegistrationRepo matchRegistrationRepo;
    @Autowired
    private TimeTableRepo  timeTableRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(TimeTableDTO timeTableDTO) {

        if(timeTableRepo.existsById(timeTableDTO.getId()))  {throw new RuntimeException("Fill Hall Already exists");}
        timeTableRepo.save(modelMapper.map(timeTableDTO, TimeTable.class));
    }

    @Override
    public ArrayList<TimeTableDTO> getAll() {
        return modelMapper.map(timeTableRepo.findAll(),new TypeToken<List<TimeTableDTO>>() {}.getType());
    }

//    @Override
//    public void delete(Long id) {
//        if(timeTableRepo.existsById(id)){timeTableRepo.deleteById(id);
//        }
//        else {
//            throw new RuntimeException("Film Hall does not exists");
//        }
//    }
public void delete(Long filmId) {
    // First delete all related film registrations
    matchRegistrationRepo.deleteAllByTimeTableId(filmId);

    // Then delete the film
    timeTableRepo.deleteById(filmId);
}
    @Override
    public void update(TimeTableDTO timeTableDTO) {
        if(timeTableRepo.existsById(timeTableDTO.getId())){
            timeTableRepo.save(modelMapper.map(timeTableDTO,TimeTable.class));
        }

        else {

            throw new RuntimeException("customer does not exists");
        }


    }
}
