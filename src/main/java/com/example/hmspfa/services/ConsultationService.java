package com.example.hmspfa.services;

import com.example.hmspfa.entities.Consultation;
import com.example.hmspfa.exceptions.ConsultationNotFoundException;

import java.util.List;

public interface ConsultationService {
    Consultation saveConsultation(Consultation consultation);
    Consultation getConsultationById(Long id) throws ConsultationNotFoundException;
    void deleteConsultation(Long id) throws ConsultationNotFoundException;
    Consultation updateConsultation(Consultation consultation) throws ConsultationNotFoundException;

    List<Consultation> getAllConsultations();
}
