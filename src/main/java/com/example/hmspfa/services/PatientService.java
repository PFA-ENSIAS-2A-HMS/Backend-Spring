package com.example.hmspfa.services;

import com.example.hmspfa.entities.Patient;

public interface PatientService {
    Patient savePatient(Patient patient);
    Patient getPatientById(Long id);
    void deletePatient(Long id);
    Patient updatePatient(Patient patient);
}
