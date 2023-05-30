package com.example.hmspfa.services;

import com.example.hmspfa.entities.Consultation;

import java.util.List;

public interface ConsultationService {
    Consultation saveConsultation(Consultation consultation);
    Consultation getConsultationById(Long id);
    void deleteConsultation(Long id);
    Consultation updateConsultation(Consultation consultation);

    List<Consultation> getAllConsultations();
}
