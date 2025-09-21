package lk.ijse.b72.finalproject.back_end.controller;

import lk.ijse.b72.finalproject.back_end.DTO.SeatTypeDTO;
import lk.ijse.b72.finalproject.back_end.service.SeatTypeService;
import lk.ijse.b72.finalproject.back_end.util.ResponceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seat-type")
@CrossOrigin
public class SeatTypeController {


    @Autowired
    private SeatTypeService seatTypeService;


    @PostMapping(path = "save" )

    public ResponceUtil getFilHall(@RequestBody SeatTypeDTO seatTypeDTO){
         seatTypeService.save(seatTypeDTO);
        return new ResponceUtil( 201,"seat type is Saved" ,null);
    }


    @GetMapping("getAll")

    private ResponceUtil getAllFilmHall(){
        return  new ResponceUtil(200,"success", seatTypeService.getAll());
    }


    @DeleteMapping(path = "delete/{id}")

    public ResponceUtil deleteCustomer(@PathVariable(value = "id") Long id){
        seatTypeService.delete(id);
        return new ResponceUtil(200,"seat type deleted" ,null);
    }





    @PutMapping(path = "update")

    public  ResponceUtil update(@RequestBody  SeatTypeDTO seatTypeDTO){
         seatTypeService.update(seatTypeDTO);
        return new ResponceUtil( 201,"seat type is updated" ,null);

    }



}
