package lk.ijse.b72.finalproject.back_end.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.b72.finalproject.back_end.DTO.StadiumDTO;
import lk.ijse.b72.finalproject.back_end.service.impl.StadiumServiceImpl;
import lk.ijse.b72.finalproject.back_end.util.ResponceUtil;
import lk.ijse.b72.finalproject.back_end.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/example")
@CrossOrigin
public class StadiumController {


    private  static final Logger log = LoggerFactory.getLogger(StadiumController.class);
    @Autowired
    private StadiumServiceImpl spiceService;
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil saveSpice(@RequestPart("spice") String spiceJson, @RequestPart("file") MultipartFile file) {
        try {
            StadiumDTO spiceDTO = new ObjectMapper().readValue(spiceJson, StadiumDTO.class);
            log.info("Received request to save spice: {}", spiceDTO.getName());
            spiceDTO.setImageURL(file.getOriginalFilename());
            StadiumDTO<String> savedSpice = spiceService.save(spiceDTO, file);
            log.info("film hall saved successfully: {}", spiceDTO.getName());
            return new ResponseUtil(201, "Spice saved successfully", savedSpice);
        } catch (Exception e) {
            log.error("Error film hall spice", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }
    @GetMapping(path = "/get")

    public ResponseUtil getAllSpiceListenings(){
        try {
            List<StadiumDTO<String>> spices = spiceService.getAll();
            return new ResponseUtil(201, "film hall retrieved successfully", spices);
        } catch (Exception e) {
            log.error("Error retrieving spices", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }



    @DeleteMapping(path = "delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponceUtil deleteCustomer(@PathVariable(value = "id") UUID id){
        spiceService.delete(id);
        return new ResponceUtil(200,"film hall deleted" ,null);
    }








    @PutMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil updateSpiceListening(@RequestPart("spice") String spiceJson, @RequestPart("file") MultipartFile file) {
        try {
            StadiumDTO spiceDTO = new ObjectMapper().readValue(spiceJson, StadiumDTO.class);
            log.info("Received request to update spice: {}", spiceDTO.getName());
            spiceDTO.setImageURL(file.getOriginalFilename());
            StadiumDTO<String> updatedSpice = spiceService.update(spiceDTO.getId(), spiceDTO, file);
            log.info("film hall updated successfully: {}", spiceDTO.getName());
            return new ResponseUtil(201, "film hall updated successfully", updatedSpice);
        } catch (Exception e) {
            log.error("Error updating spice", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }






    }
