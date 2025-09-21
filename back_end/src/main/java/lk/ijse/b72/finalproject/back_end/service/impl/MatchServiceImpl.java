package lk.ijse.b72.finalproject.back_end.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.b72.finalproject.back_end.DTO.MatchDTO;
import lk.ijse.b72.finalproject.back_end.Entity.Match;
import lk.ijse.b72.finalproject.back_end.enums.ImageType;
import lk.ijse.b72.finalproject.back_end.repo.MatchRegistrationRepo;
import lk.ijse.b72.finalproject.back_end.repo.Matchrepo;
import lk.ijse.b72.finalproject.back_end.service.MatchService;
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
public class MatchServiceImpl implements MatchService {


//
//    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);
//    @Autowired
//    private FilmRepo filmRepo;
//    @Autowired
//    private ModelMapper modelMapper;
//    @Autowired
//    private ImageUtil imageUtil;

//    @Override
//    @Transactional
//    public FilmDTO<String> save(FilmDTO spiceDTO, MultipartFile file) {
//        String base64Image = imageUtil.saveImage( ImageType.FILM,file);
//        logger.info("Base64 image: {}", base64Image);
//        Film spice = modelMapper.map(spiceDTO, Film.class);
//        spice.setImageUrl(base64Image);
//        try {
//            Film savedSpice = filmRepo.save(spice);
//            FilmDTO<String> stringSpiceDTO = modelMapper.map(savedSpice, FilmDTO.class);
//            stringSpiceDTO.setImageUrl(base64Image);
//            return stringSpiceDTO;
//        } catch (Exception e) {
//            logger.error("Failed to save spice: {}", spice, e);
//            throw new RuntimeException("Failed to save spice");
//        }
//    }
//
//
//    @Override
//    public List<FilmDTO<String>> getAll() {
//        List<Film> spices = filmRepo.findAll();
//        List<FilmDTO<String>> spiceDTOS = modelMapper.map(spices, new TypeToken<List<FilmDTO<String>>>() {}.getType());
//        for (FilmDTO<String> spiceDTO : spiceDTOS) {
//            spices.stream().filter(spice ->
//                            spice.getId().equals(spiceDTO.getId()))
//                    .findFirst()
//                    .ifPresent(spice -> spiceDTO.setImageUrl(imageUtil.getImage(spice.getImageUrl())));
//        }
//        return spiceDTOS;
//    }


    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Autowired
    private Matchrepo matchrepo;
    @Autowired
    private MatchRegistrationRepo matchRegistrationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Override
    @Transactional
    public MatchDTO<String> save(MatchDTO matchDTO, MultipartFile file) {
        String base64Image;
        try {
            base64Image = imageUtil.saveImage(ImageType.MATCH, file);
            logger.info("Base64 image: {}", base64Image);
        } catch (Exception e) {
            logger.error("Failed to save image", e);
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }

        Match match = modelMapper.map(matchDTO, Match.class);
        match.setImageUrl(base64Image);
        try {
            Match savedFilm = matchrepo.save(match);
            MatchDTO<String> stringFilmDTO = modelMapper.map(savedFilm, MatchDTO.class);
            stringFilmDTO.setImageUrl(base64Image);
            return stringFilmDTO;
        } catch (Exception e) {
            logger.error("Failed to save match: {}", match, e);
            throw new RuntimeException("Failed to save match: " + e.getMessage());
        }
    }

    @Override
    public List<MatchDTO<String>> getAll() {
        List<Match> matches = matchrepo.findAll();
        List<MatchDTO<String>> filmDTOs = modelMapper.map(matches, new TypeToken<List<MatchDTO<String>>>() {
        }.getType());
        for (MatchDTO<String> filmDTO : filmDTOs) {
            matches.stream().filter(match ->
                            match.getId().equals(filmDTO.getId()))
                    .findFirst()
                    .ifPresent(match -> filmDTO.setImageUrl(imageUtil.getImage(match.getImageUrl())));
        }
        return filmDTOs;
    }

//    @Override
//    public void delete(UUID id) {
//        if (filmRepo.existsById(id)) {
//            filmRepo.deleteById(id);
//        } else {
//        .//    throw new RuntimeException("Film Hall does not ");
//        }
//    }

//@Override
//   // @Transactional
//    public void delete(UUID filmId) {
//        Film film = filmRepo.findById(filmId)
//                .orElseThrow(() -> new EntityNotFoundException("Film not found"));
//
//        film.removeFilmRegistrations();
//        filmRepo.save(film); // Save the disassociation
//        filmRepo.delete(film); // Now safe to delete
//    }

    public void delete(UUID filmId) {
        // First delete all related film registrations
        matchRegistrationRepo.deleteAllByFilmId(filmId);

        // Then delete the film
        matchrepo.deleteById(filmId);
    }

    @Override
    public MatchDTO update(UUID id, MatchDTO matchDTO, MultipartFile file) {
        Optional<Match> matchOpt = matchrepo.findById(id);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            String imageName = match.getImageUrl();
            if (!file.isEmpty()) {
                imageName = imageUtil.updateImage(match.getImageUrl(), ImageType.MATCH, file);
            }
            match.setImageUrl(imageName);
            match.setTitle(matchDTO.getTitle());
            match.setDescription(matchDTO.getDescription());
            match.setGenre(matchDTO.getGenre());
            match.setTeamA(matchDTO.getTeamA());
            match.setTeamB(matchDTO.getTeamB());
            match.setDurationMinutes(matchDTO.getDurationMinutes());
            match.setMatchDate(matchDTO.getMatchDate());
            match.setStadium(matchDTO.getStadium());
            match.setImageUrl(matchDTO.getImageUrl());
            try {
                matchrepo.save(match);
                logger.info("Match updated successfully: {}", match);
                return modelMapper.map(match, MatchDTO.class);
            } catch (StaleObjectStateException e) {
                logger.error("Failed to update match: {}", match, e);
                throw new RuntimeException("Failed to update match");
            }
        } else {
            logger.warn("Match with id {} not found", id);
            throw new RuntimeException("Match Listing Not Found");
        }
    }






    public MatchDTO<String> getFilmImage(UUID filmId) {
        Optional<Match> matchOptional = matchrepo.findById(filmId);
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            return new MatchDTO(
                    match.getId(),
                    match.getTitle(),
                    match.getDescription(),
                    match.getGenre(),
                    match.getTeamA(),
                    match.getTeamB(),
                    match.getDurationMinutes(),
                    match.getMatchDate(),
                    match.getStadium(),
                    match.getImageUrl()
            );

        }
        return null; // or throw an exception
    }

}















