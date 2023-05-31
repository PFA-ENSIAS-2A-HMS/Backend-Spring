package com.example.hmspfa.services;

import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.exceptions.HospitalNotFoundException;

import java.util.List;

public interface HospitalService {
    Hospital saveHospital(Hospital hospital);
    Hospital getHospitalById(Long id) throws HospitalNotFoundException;
    void deleteHospital(Long id);
    Hospital updateHospital(Hospital hospital);
    List<Hospital> getAllHospitals();
}
