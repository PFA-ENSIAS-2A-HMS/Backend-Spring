package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
