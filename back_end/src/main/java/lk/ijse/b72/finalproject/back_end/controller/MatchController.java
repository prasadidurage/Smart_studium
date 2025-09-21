package lk.ijse.b72.finalproject.back_end.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.b72.finalproject.back_end.DTO.MatchDTO;
import lk.ijse.b72.finalproject.back_end.service.impl.MatchServiceImpl;
import lk.ijse.b72.finalproject.back_end.util.ResponceUtil;
import lk.ijse.b72.finalproject.back_end.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/film")
@CrossOrigin
public class MatchController {




    private  static final Logger log = LoggerFactory.getLogger(MatchController.class);
    @Autowired
    private MatchServiceImpl spiceService;










    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseUtil saveSpice(@RequestPart("spice") String spiceJson, @RequestPart("file") MultipartFile file) {
        try {
            MatchDTO spiceDTO = new ObjectMapper().readValue(spiceJson, MatchDTO.class);
            log.info("Received request to save spice: {}", spiceDTO.getTitle());
            spiceDTO.setImageUrl(file.getOriginalFilename());
            MatchDTO<String> savedSpice = spiceService.save(spiceDTO, file);
            log.info("film saved successfully: {}", spiceDTO.getTitle());
            return new ResponseUtil(201, "film saved successfully", savedSpice);
        } catch (Exception e) {
            log.error("Error saving spice", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }
    @GetMapping(path = "/get")

    public ResponseUtil getAllSpiceListenings(){
        try {
            List<MatchDTO<String>> spices = spiceService.getAll();
            return new ResponseUtil(201, "film retrieved successfully", spices);
        } catch (Exception e) {
            log.error("Error retrieving spices", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }



    @DeleteMapping(path = "delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponceUtil deleteCustomer(@PathVariable(value = "id") UUID id){
        spiceService.delete(id);
        return new ResponceUtil(200,"film  deleted" ,null);
    }








    @PutMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil updateSpiceListening(@RequestPart("spice") String spiceJson, @RequestPart("file") MultipartFile file) {
        try {
            MatchDTO spiceDTO = new ObjectMapper().readValue(spiceJson, MatchDTO.class);
            log.info("Received request to update spice: {}", spiceDTO.getTitle());
            spiceDTO.setImageUrl(file.getOriginalFilename());
            MatchDTO<String> updatedSpice = spiceService.update(spiceDTO.getId(), spiceDTO, file);
            log.info("film updated successfully: {}", spiceDTO.getTitle());
            return new ResponseUtil(201, "film updated successfully", updatedSpice);
        } catch (Exception e) {
            log.error("Error updating spice", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }




    @GetMapping("/getImage/{id}")
    public ResponseEntity<MatchDTO<String>> getFilmImage(@PathVariable("id") UUID filmId) {
        MatchDTO<String> filmDTO = spiceService.getFilmImage(filmId);
        if (filmDTO != null) {
            return ResponseEntity.ok(filmDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
