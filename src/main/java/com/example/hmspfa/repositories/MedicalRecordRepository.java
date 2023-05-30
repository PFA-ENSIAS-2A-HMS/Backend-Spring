package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {
}
