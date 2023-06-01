package com.example.hmspfa.services;

import com.example.hmspfa.entities.DashboardInfo;
import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.exceptions.HospitalNotFoundException;

import java.util.List;

public interface HospitalService {
    Hospital saveHospital(Hospital hospital, Long userId);
    Hospital getHospitalById(Long id) throws HospitalNotFoundException;
    void deleteHospital(Long id) throws HospitalNotFoundException;
    Hospital updateHospital(Hospital hospital) throws HospitalNotFoundException;
    List<Hospital> getAllHospitals();
    DashboardInfo getDashboardInfo(Long hospitalId)  throws HospitalNotFoundException ;


}
