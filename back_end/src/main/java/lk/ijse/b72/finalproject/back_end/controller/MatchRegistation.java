package lk.ijse.b72.finalproject.back_end.controller;

import lk.ijse.b72.finalproject.back_end.DTO.MatchRegisterDTO;
import lk.ijse.b72.finalproject.back_end.service.MatchRegistationService;
import lk.ijse.b72.finalproject.back_end.util.ResponceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/film-registration")
@CrossOrigin
public class MatchRegistation {


    @Autowired
    private MatchRegistationService matchRegistationService;


    @PostMapping(path = "save")
   // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponceUtil getFilHall(@RequestBody MatchRegisterDTO matchRegisterDTO) {
        matchRegistationService.save(matchRegisterDTO);
        return new ResponceUtil(201, "film registration is Saved", null);
    }

    @GetMapping("getAll")

    private ResponceUtil getAllFilmHall() {
        return new ResponceUtil(200, "success", matchRegistationService.getAll());
    }


    @PutMapping(path = "update")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public  ResponceUtil update(@RequestBody  MatchRegisterDTO matchRegisterDTO){
         matchRegistationService.update(matchRegisterDTO);
        return new ResponceUtil( 201,"registration is updated" ,null);

    }



    @DeleteMapping(path = "delete/{id}")
  //  @PreAuthorize("hasAuthority('ADMIN')")
    public ResponceUtil deleteCustomer(@PathVariable(value = "id") Long id) {
        try {
            matchRegistationService.delete(id);
            return new ResponceUtil(200, "Film registration deleted", null);
        } catch (RuntimeException e) {
            return new ResponceUtil(400, e.getMessage(), null);
        }
    }



}



