package com.example.hmspfa.web;

import com.example.hmspfa.entities.MedicalRecord;
import com.example.hmspfa.services.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicalrecords")
@AllArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createdMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicalRecord);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable("id") Long id) {
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);
        if (medicalRecord != null) {
            return ResponseEntity.ok(medicalRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable("id") Long id, @RequestBody MedicalRecord medicalRecord) {
        medicalRecord.setId(id);
        MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecord(medicalRecord);
        if (updatedMedicalRecord != null) {
            return ResponseEntity.ok(updatedMedicalRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable("id") Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }
}
