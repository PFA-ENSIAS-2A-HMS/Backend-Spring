package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.MedicalRecord;
import com.example.hmspfa.repositories.MedicalRecordRepository;
import com.example.hmspfa.services.MedicalRecordService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }
}
