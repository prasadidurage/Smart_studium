package lk.ijse.b72.finalproject.back_end.service;

import lk.ijse.b72.finalproject.back_end.DTO.SeatDTO;

import java.util.ArrayList;
import java.util.Map;

public interface SeatService {

    public  void save(SeatDTO seatsDTO);

    public ArrayList<Map<String, Object>> getAll();
    public void delete(Long id);
    public void update(SeatDTO seatDTO);
}
