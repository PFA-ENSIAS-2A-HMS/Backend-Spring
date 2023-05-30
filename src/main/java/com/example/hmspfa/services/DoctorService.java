package com.example.hmspfa.services;

import com.example.hmspfa.entities.Doctor;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
    Doctor updateDoctor(Doctor doctor);
}
