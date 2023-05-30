package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.services.HospitalService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService {
    @Override
    public Hospital saveHospital(Hospital hospital) {
        return null;
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return null;
    }

    @Override
    public void deleteHospital(Long id) {

    }

    @Override
    public Hospital updateHospital(Hospital hospital) {
        return null;
    }
}
