package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository  extends JpaRepository<Patient,Long> {
    @Query("SELECT p FROM Patient p WHERE p.phoneNumber = :phoneNumber")
    Patient findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
