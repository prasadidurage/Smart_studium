package lk.ijse.b72.finalproject.back_end.service;

import lk.ijse.b72.finalproject.back_end.DTO.MatchDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface MatchService {


    MatchDTO<String> save(MatchDTO spiceDTO, MultipartFile file);
    List<MatchDTO<String>> getAll();

    public void delete(UUID id);

    public MatchDTO<String> getFilmImage(UUID filmId);


   public MatchDTO<String> update(UUID id, MatchDTO spiceDTO, MultipartFile file);

}
