package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.services.DoctorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return null;
    }

    @Override
    public void deleteDoctor(Long id) {

    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return null;
    }
}
