package com.example.hmspfa.services;

import com.example.hmspfa.entities.MedicalRecord;

public interface MedicalRecordService {
    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord getMedicalRecordById(Long id);
    void deleteMedicalRecord(Long id);
    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);
}
