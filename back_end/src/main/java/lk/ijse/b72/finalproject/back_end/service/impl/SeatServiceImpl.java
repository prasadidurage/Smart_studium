package lk.ijse.b72.finalproject.back_end.service.impl;

import lk.ijse.b72.finalproject.back_end.DTO.SeatDTO;
import lk.ijse.b72.finalproject.back_end.Entity.SeatType;
import lk.ijse.b72.finalproject.back_end.Entity.Seats;
import lk.ijse.b72.finalproject.back_end.repo.SeatRepo;
import lk.ijse.b72.finalproject.back_end.repo.SeatTypeRepo;
import lk.ijse.b72.finalproject.back_end.service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatServiceImpl implements SeatService {


    @Autowired
    private SeatRepo seatRepo;
    @Autowired
    private SeatTypeRepo seatTypeRepo;
    @Autowired
    private ModelMapper modelMapper;

//
//    @Override
//    public void save(SeatsDTO seatsDTO) {
//
//
//
//
//        if( seatRepo.existsById(seatsDTO.getId()))  {throw new RuntimeException("Fill Hall Already exists");}
//       seatRepo.save(modelMapper.map(seatsDTO, Seats.class));
//
//    }

    @Override
    public void save(SeatDTO seatsDTO) {
        if (seatsDTO.getStadium() == null || seatsDTO.getSeatType().getId() == null) {
            throw new RuntimeException("Seat Type cannot be null");
        }

        // Check if the SeatType exists
        if (!seatTypeRepo.existsById(seatsDTO.getSeatType().getId())) {

            throw new RuntimeException("Seat Type does not exist");
        }

        if (seatRepo.existsById(seatsDTO.getId())) {
            throw new RuntimeException("Seat already exists");
        }

        seatRepo.save(modelMapper.map(seatsDTO, Seats.class));
    }

    @Override
    public ArrayList<Map<String, Object>> getAll() {
        List<Seats> filmRegistrations = seatRepo.findAll();
        ArrayList<Map<String, Object>> result = new ArrayList<>();

        for ( Seats registration : filmRegistrations) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", registration.getId());
            item.put("rowLetter", registration.getRowLatter());
            item.put("seatNumber", registration.getSeatNumber());
            item.put("isAvailable", registration.isAvailable());
            item.put("seatType", registration.getSeatType().getType());
            item.put("filmHall", registration.getStadium().getName());

            result.add(item);
        }

        return result;
    }



    public void delete(Long id) {
        Seats seat = seatRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Seat does not exist"));

        // Break bi-directional associations
        SeatType seatType = seat.getSeatType();
        if (seatType != null && seatType.getSeats() != null) {
            seatType.getSeats().remove(seat);
        }

        seat.setSeatType(null);
        //seat.getStadium(null);   dn comment kre eka hadann

        // Save first to update associations
        seatRepo.save(seat);

        // Then delete
        seatRepo.deleteById(id);



    }



    @Override
    public void update(SeatDTO seatDTO) {
        if(seatRepo.existsById(seatDTO.getId())){
            seatRepo.save(modelMapper.map(seatDTO,Seats.class));
        }

        else {

            throw new RuntimeException("customer does not exists");
        }


    }
}
