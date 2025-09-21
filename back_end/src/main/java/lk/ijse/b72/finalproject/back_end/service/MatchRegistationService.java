package lk.ijse.b72.finalproject.back_end.service;

import lk.ijse.b72.finalproject.back_end.DTO.MatchRegisterDTO;

import java.util.ArrayList;
import java.util.Map;

public interface MatchRegistationService {

    public  void save(MatchRegisterDTO matchRegisterDTO);
    //public ArrayList<FilmRegistrationDTO> getAll();
    public ArrayList<Map<String, Object>> getAll();
    public void update(MatchRegisterDTO filmRegistrationDTO);
    public void delete(Long id);}