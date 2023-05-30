package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.services.PatientService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {
    @Override
    public Patient savePatient(Patient patient) {
        return null;
    }

    @Override
    public Patient getPatientById(Long id) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {

    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }
}
