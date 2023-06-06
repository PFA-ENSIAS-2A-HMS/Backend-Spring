package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.Appointment;
import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findAppointmentByDoctor(Doctor doctor);
    List<Appointment> findByHospital(Hospital hospital);
}
