package lk.ijse.b72.finalproject.back_end.service;

import lk.ijse.b72.finalproject.back_end.DTO.StadiumDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface StadiumService {

    StadiumDTO<String> save(StadiumDTO spiceDTO, MultipartFile file);
    List<StadiumDTO<String>> getAll();

    public void delete(UUID id);



    public StadiumDTO<String> update(UUID id, StadiumDTO spiceDTO, MultipartFile file);

}
