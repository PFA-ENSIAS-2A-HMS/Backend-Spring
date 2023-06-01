package com.example.hmspfa.services;

import com.example.hmspfa.entities.MedicalRecord;
import com.example.hmspfa.exceptions.MedicalRecordNotFoundException;

public interface MedicalRecordService {
    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord getMedicalRecordById(Long id) throws MedicalRecordNotFoundException;
    void deleteMedicalRecord(Long id) throws MedicalRecordNotFoundException;
    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException;
}
