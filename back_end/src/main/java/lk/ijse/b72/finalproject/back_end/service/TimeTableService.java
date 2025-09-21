package lk.ijse.b72.finalproject.back_end.service;

import lk.ijse.b72.finalproject.back_end.DTO.TimeTableDTO;

import java.util.ArrayList;

public interface TimeTableService {


    public  void save(TimeTableDTO timeTableDTO);

    public ArrayList<TimeTableDTO> getAll();
    public void delete(Long id);
    public void update( TimeTableDTO  timeTableDTO);
}
