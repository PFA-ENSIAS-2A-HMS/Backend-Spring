package com.example.hmspfa.services;

import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.exceptions.PatientNotFoundException;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient, Long hospitalId);
    Patient getPatientById(Long id) throws PatientNotFoundException;
    void deletePatient(Long id) throws PatientNotFoundException;
    Patient updatePatient(Patient patient) throws PatientNotFoundException;

    List<Patient> getAllPatients();

    Patient getPatientByPhoneNumber(String phoneNumber);
}
