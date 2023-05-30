package com.example.hmspfa.services;

import com.example.hmspfa.entities.Consultation;

public interface ConsultationService {
    Consultation saveConsultation(Consultation consultation);
    Consultation getConsultationById(Long id);
    void deleteConsultation(Long id);
    Consultation updateConsultation(Consultation consultation);
}
