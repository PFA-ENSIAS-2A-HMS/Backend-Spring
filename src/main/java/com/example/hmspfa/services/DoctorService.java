package com.example.hmspfa.services;

import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.exceptions.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor, Long hospitalId);
    Doctor getDoctorById(Long id) throws DoctorNotFoundException;
    void deleteDoctor(Long id)  throws DoctorNotFoundException;
    Doctor updateDoctor(Doctor doctor) throws DoctorNotFoundException;

    List<Doctor> getAllDoctors();

}
