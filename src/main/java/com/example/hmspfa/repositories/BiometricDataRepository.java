package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.BiometricData;
import com.example.hmspfa.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiometricDataRepository extends JpaRepository<BiometricData,Long> {
   BiometricData findByPatient(Patient patient);
}
