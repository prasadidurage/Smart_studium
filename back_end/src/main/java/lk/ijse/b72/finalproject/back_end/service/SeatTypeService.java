package lk.ijse.b72.finalproject.back_end.service;

import lk.ijse.b72.finalproject.back_end.DTO.SeatTypeDTO;

import java.util.ArrayList;

public interface SeatTypeService {


    public  void save(SeatTypeDTO seatTypeDTO);

    public ArrayList<SeatTypeDTO> getAll();
    public void delete(Long id);
    public void update(SeatTypeDTO seatTypeDTO);
}
