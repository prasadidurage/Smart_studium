package lk.ijse.b72.finalproject.back_end.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.b72.finalproject.back_end.DTO.StadiumDTO;
import lk.ijse.b72.finalproject.back_end.Entity.Stadium;
import lk.ijse.b72.finalproject.back_end.enums.ImageType;
import lk.ijse.b72.finalproject.back_end.repo.Stadiumrepo;
import lk.ijse.b72.finalproject.back_end.repo.MatchRegistrationRepo;
import lk.ijse.b72.finalproject.back_end.service.StadiumService;
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
public class StadiumServiceImpl implements StadiumService {
    private static final Logger logger = LoggerFactory.getLogger(StadiumServiceImpl.class);
    @Autowired
    private Stadiumrepo stadiumrepo;

    @Autowired
    private MatchRegistrationRepo matchRegistrationRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ImageUtil imageUtil;

    @Override
    @Transactional
    public StadiumDTO<String> save(StadiumDTO spiceDTO, MultipartFile file) {
        String base64Image = imageUtil.saveImage( ImageType.EXAMPLE,file);
        logger.info("Base64 image: {}", base64Image);
        Stadium spice = modelMapper.map(spiceDTO, Stadium.class);
        spice.setImageURL(base64Image);
        try {
            Stadium savedSpice = stadiumrepo.save(spice);
            StadiumDTO<String> stringSpiceDTO = modelMapper.map(savedSpice, StadiumDTO.class);
            stringSpiceDTO.setImageURL(base64Image);
            return stringSpiceDTO;
        } catch (Exception e) {
            logger.error("Failed to save spice: {}", spice, e);
            throw new RuntimeException("Failed to save spice");
        }
    }

    @Override
    public List<StadiumDTO<String>> getAll() {
        List<Stadium> spices = stadiumrepo.findAll();
        List<StadiumDTO<String>> spiceDTOS = modelMapper.map(spices, new TypeToken<List<StadiumDTO<String>>>() {}.getType());
        for (StadiumDTO<String> spiceDTO : spiceDTOS) {
            spices.stream().filter(spice ->
                            spice.getId().equals(spiceDTO.getId()))
                    .findFirst()
                    .ifPresent(spice -> spiceDTO.setImageURL(imageUtil.getImage(spice.getImageURL())));
        }
        return spiceDTOS;
    }



//    @Override
//    public void delete(UUID id) {
//        if(filmHallRepo.existsById(id)){filmHallRepo.deleteById(id);
//        }
//        else {
//            throw new RuntimeException("Film Hall does not ");
//        }
//    }

    public void delete(UUID filmId) {
        // First delete all related film registrations
        matchRegistrationRepo.deleteAllByFilmHallId(filmId);

        // Then delete the film
         stadiumrepo.deleteById(filmId);
    }

    @Override
    public StadiumDTO<String> update(UUID id, StadiumDTO spiceDTO, MultipartFile file) {
        Optional<Stadium> spice = stadiumrepo.findById(id);
        if (spice.isPresent()) {
            String imageName = spice.get().getImageURL();
            if (!file.isEmpty()) {
                imageName = imageUtil.updateImage(spice.get().getImageURL(), ImageType.STADIUM, file);
            }
            spice.get().setImageURL(imageName);
            spice.get().setName(spiceDTO.getName());
            spice.get().setDescription(spiceDTO.getDescription());
            spice.get().setContactNumber(spiceDTO.getContactNumber());
            spice.get().setEmail(spiceDTO.getEmail());
            spice.get().setLocation(spiceDTO.getLocation());
            try {
                stadiumrepo.save(spice.get());
                logger.info("Spice updated successfully: {}", spice);
                return modelMapper.map(spice, StadiumDTO.class);
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
