package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Hospital;

import com.example.hmspfa.enums.HospitalStatus;
import com.example.hmspfa.exceptions.HospitalNotFoundExeption;
import com.example.hmspfa.mappers.HospitalMapperImpl;
import com.example.hmspfa.repositories.HospitalRepository;
import com.example.hmspfa.services.HospitalService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final HospitalMapperImpl hospitalMapper;
    @Override
    public Hospital saveHospital(Hospital hospital) {
        hospital.setStatus(HospitalStatus.CREATED);
        return hospitalRepository.save(hospital);
    }

    @Override
    public Hospital getHospitalById(Long id) throws HospitalNotFoundExeption {
        Optional<Hospital> optionalHospital = Optional.ofNullable(hospitalRepository.findById(id).orElseThrow(
                () -> new HospitalNotFoundExeption("Hospital Not Found !")
        ));
        return optionalHospital.orElse(null);
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public Hospital updateHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}
