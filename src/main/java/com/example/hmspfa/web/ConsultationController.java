package com.example.hmspfa.web;

import com.example.hmspfa.entities.Consultation;
import com.example.hmspfa.services.ConsultationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/consultations")
@AllArgsConstructor
public class ConsultationController {
    private final ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<Consultation> saveConsultation(@RequestBody Consultation consultation) {
        Consultation savedConsultation = consultationService.saveConsultation(consultation);
        return new ResponseEntity<>(savedConsultation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable("id") Long id) {
        Consultation consultation = consultationService.getConsultationById(id);
        if (consultation != null) {
            return new ResponseEntity<>(consultation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable("id") Long id) {
        consultationService.deleteConsultation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable("id") Long id, @RequestBody Consultation consultation) {
        Consultation existingConsultation = consultationService.getConsultationById(id);
        if (existingConsultation != null) {
            consultation.setId(id);
            Consultation updatedConsultation = consultationService.updateConsultation(consultation);
            return new ResponseEntity<>(updatedConsultation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> consultations = consultationService.getAllConsultations();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }
}
