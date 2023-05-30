package com.example.hmspfa.services;

import com.example.hmspfa.entities.Patient;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);
    Patient getPatientById(Long id);
    void deletePatient(Long id);
    Patient updatePatient(Patient patient);

    List<Patient> getAllPatients();
}
