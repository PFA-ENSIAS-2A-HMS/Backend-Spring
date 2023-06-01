package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.MedicalRecord;
import com.example.hmspfa.exceptions.MedicalRecordNotFoundException;
import com.example.hmspfa.repositories.MedicalRecordRepository;
import com.example.hmspfa.services.MedicalRecordService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public MedicalRecord getMedicalRecordById(Long id) throws MedicalRecordNotFoundException {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medical Record Not Found"));
    }


    @Override
    public void deleteMedicalRecord(Long id) throws MedicalRecordNotFoundException {
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordRepository.findById(id);

        if (medicalRecordOptional.isEmpty()) {
            throw new MedicalRecordNotFoundException("Medical Record Not Found");
        }

        medicalRecordRepository.deleteById(id);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordRepository.findById(medicalRecord.getId());

        if (medicalRecordOptional.isEmpty()) {
            throw new MedicalRecordNotFoundException("Medical Record Not Found");
        }
        return medicalRecordRepository.save(medicalRecord);
    }
}
