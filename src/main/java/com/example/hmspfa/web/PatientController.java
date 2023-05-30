package com.example.hmspfa.web;

import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient != null) {
            patient.setId(id);
            Patient updatedPatient = patientService.updatePatient(patient);
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
}
