package lk.ijse.b72.finalproject.back_end.service;

import lk.ijse.b72.finalproject.back_end.DTO.BookingDTO;

import java.util.ArrayList;

public interface BookingService {

    public  void save(BookingDTO seatTypeDTO);

    public ArrayList<BookingDTO> getAll();
    public void delete(Long id);
    public void update(BookingDTO seatTypeDTO);
}
