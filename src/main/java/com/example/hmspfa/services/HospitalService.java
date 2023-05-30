package com.example.hmspfa.services;

import com.example.hmspfa.entities.Hospital;

public interface HospitalService {
    Hospital saveHospital(Hospital hospital);
    Hospital getHospitalById(Long id);
    void deleteHospital(Long id);
    Hospital updateHospital(Hospital hospital);
}
