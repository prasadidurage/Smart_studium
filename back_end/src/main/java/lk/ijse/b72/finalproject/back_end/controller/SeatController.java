package lk.ijse.b72.finalproject.back_end.controller;

import lk.ijse.b72.finalproject.back_end.DTO.SeatDTO;
import lk.ijse.b72.finalproject.back_end.service.SeatService;
import lk.ijse.b72.finalproject.back_end.util.ResponceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seat")
@CrossOrigin
public class   SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping(path = "save")

    public ResponceUtil getFilHall(@RequestBody SeatDTO seatsDTO) {
       seatService.save(seatsDTO);
        return new ResponceUtil(201, "seat is Saved", null);
    }

    @GetMapping("getAll")
    private ResponceUtil getAllFilmHall() {
        return new ResponceUtil(200, "success", seatService.getAll());
    }


    @PutMapping(path = "update")

    public  ResponceUtil update(@RequestBody SeatDTO seatDTO){
         seatService.update(seatDTO);
        return new ResponceUtil( 201,"seat is updated" ,null);

    }

        @DeleteMapping(path = "delete/{id}")

    public ResponceUtil deleteCustomer(@PathVariable(value = "id") Long id){
         seatService.delete(id);
        return new ResponceUtil(200," seat deleted" ,null);
    }

}
