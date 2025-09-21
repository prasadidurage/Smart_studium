package lk.ijse.b72.finalproject.back_end.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.b72.finalproject.back_end.service.UpcommingMatchService;
import lk.ijse.b72.finalproject.back_end.DTO.UpcommingMatchDTO;
import lk.ijse.b72.finalproject.back_end.Entity.UpcomingMatch;
import lk.ijse.b72.finalproject.back_end.enums.ImageType;
import lk.ijse.b72.finalproject.back_end.repo.UpcomingMatchRepo;
import lk.ijse.b72.finalproject.back_end.util.ImageUtil;
import org.hibernate.StaleObjectStateException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpcomingMatchServiceImpl implements UpcommingMatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Autowired
    private UpcomingMatchRepo matchRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Override
    @Transactional
    public UpcommingMatchDTO<String> save(UpcommingMatchDTO matchDTO, MultipartFile file) {
        String base64Image;
        try {
            base64Image = imageUtil.saveImage(ImageType.UPMATCH, file);
            logger.info("Base64 image: {}", base64Image);
        } catch (Exception e) {
            logger.error("Failed to save image", e);
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }

        UpcomingMatch match = modelMapper.map(matchDTO, UpcomingMatch.class);
        match.setImageUrl(base64Image);
        try {
            UpcomingMatch saveMatch = matchRepo.save(match);
            UpcommingMatchDTO<String> stringMatchDTO = modelMapper.map(saveMatch, UpcommingMatchDTO.class);
            stringMatchDTO.setImageUrl(base64Image);
            return stringMatchDTO;
        } catch (Exception e) {
            logger.error("Failed to save film: {}", match, e);
            throw new RuntimeException("Failed to save film: " + e.getMessage());
        }
    }

    @Override
    public List<UpcommingMatchDTO<String>> getAll() {
        List<UpcomingMatch> matches = matchRepo.findAll();
        List<UpcommingMatchDTO<String>> filmDTOs = modelMapper.map(matches, new TypeToken<List<UpcommingMatchDTO<String>>>() {}.getType());
        for (UpcommingMatchDTO<String> filmDTO : filmDTOs) {
            matches.stream().filter(film ->
                            film.getId().equals(filmDTO.getId()))
                    .findFirst()
                    .ifPresent(film -> filmDTO.setImageUrl(imageUtil.getImage(film.getImageUrl())));
        }
        return filmDTOs;
    }

    @Override
    public void delete(UUID id) {
        if(matchRepo.existsById(id)){matchRepo.deleteById(id);
        }
        else {
            throw new RuntimeException("Film Hall does not exists");
        }
    }

    @Override
    public UpcommingMatchDTO<String> update(UUID id, UpcommingMatchDTO spiceDTO, MultipartFile file) {
        Optional<UpcomingMatch> spice = matchRepo.findById(id);
        if (spice.isPresent()) {
            String imageName = spice.get().getImageUrl();
            if (!file.isEmpty()) {
                imageName = imageUtil.updateImage(spice.get().getImageUrl(), ImageType.UPMATCH, file);
            }
            spice.get().setImageUrl(imageName);
            spice.get().setTitle(spiceDTO.getTitle());
            spice.get().setDescription(spiceDTO.getDescription());
            spice.get().setGenre(spiceDTO.getGenre());
            spice.get().setTeam(spiceDTO.getTeam());


            spice.get().setDurationMinutes(spiceDTO.getDurationMinutes());

            spice.get().setDate(spiceDTO.getDate());
            spice.get().setMatchTime(spiceDTO.getMatchTime());
            spice.get().setCast(spiceDTO.getCast());
            try {
                matchRepo.save(spice.get());
                logger.info("Spice updated successfully: {}", spice);
                return modelMapper.map(spice, UpcommingMatchDTO.class);
            } catch (StaleObjectStateException e) {
                logger.error("Failed to update spice: {}", spice, e);
                throw new RuntimeException("Failed to update spice");
            }
        } else {
            logger.warn("Spice with id {} not found", id);
            throw new RuntimeException("Spice Listing Not Found");
        }
    }

}

