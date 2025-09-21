package lk.ijse.b72.finalproject.back_end.service;

import lk.ijse.b72.finalproject.back_end.DTO.UpcommingMatchDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UpcommingMatchService {



    //UpcommingMatchDTO <String> save( UpcommingMatchService spiceDTO, MultipartFile file);
    UpcommingMatchDTO<String> save(UpcommingMatchDTO spiceDTO, MultipartFile file);

    List<UpcommingMatchDTO<String>> getAll();

    public void delete(UUID id);



    public UpcommingMatchDTO<String> update(UUID id, UpcommingMatchDTO spiceDTO, MultipartFile file);
}
