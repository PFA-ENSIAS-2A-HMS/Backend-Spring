package com.example.hmspfa.services;

import com.example.hmspfa.dtos.HospitalDTO;
import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.exceptions.HospitalNotFoundExeption;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HospitalService {
    Hospital saveHospital(Hospital hospital);
    Hospital getHospitalById(Long id) throws HospitalNotFoundExeption;
    void deleteHospital(Long id);
    Hospital updateHospital(Hospital hospital);
    List<Hospital> getAllHospitals();
}
