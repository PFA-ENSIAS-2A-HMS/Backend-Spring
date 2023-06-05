package com.example.hmspfa.resources.responses;

import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.entities.Hospital;

public class DoctorHospitalResponse {
    private Doctor doctor;
    private Hospital hospital;

    public DoctorHospitalResponse(Doctor doctor, Hospital hospital) {
        this.doctor = doctor;
        this.hospital = hospital;
    }

    // Getters et setters pour doctor et hospital
}