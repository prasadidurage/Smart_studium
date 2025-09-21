package lk.ijse.b72.finalproject.back_end.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.b72.finalproject.back_end.DTO.BookingDTO;
import lk.ijse.b72.finalproject.back_end.Entity.Booking;
import lk.ijse.b72.finalproject.back_end.repo.BookingRepo;
import lk.ijse.b72.finalproject.back_end.service.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {



    @Autowired
    private BookingRepo timeTableRepo;

    @Autowired
    private ModelMapper modelMapper;
@Transactional
@Override
public void save(BookingDTO timeTableDTO) {

        if(timeTableRepo.existsById(timeTableDTO.getId()))  {throw new RuntimeException("Fill Hall Already exists");}
        timeTableRepo.save(modelMapper.map(timeTableDTO, Booking.class));
    }

    @Override
    public ArrayList<BookingDTO> getAll() {
        return modelMapper.map(timeTableRepo.findAll(),new TypeToken<List<BookingDTO>>() {}.getType());
    }

    @Override
    public void delete(Long id) {
        if(timeTableRepo.existsById(id)){timeTableRepo.deleteById(id);
        }
        else {
            throw new RuntimeException("Film Hall does not exists");
        }
    }

    @Override
    public void update(BookingDTO timeTableDTO) {
        if(timeTableRepo.existsById(timeTableDTO.getId())){
            timeTableRepo.save(modelMapper.map(timeTableDTO,Booking.class));
        }

        else {

            throw new RuntimeException("customer does not exists");
        }


    }
}
