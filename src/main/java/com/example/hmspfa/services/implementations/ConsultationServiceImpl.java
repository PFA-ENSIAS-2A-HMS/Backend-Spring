package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Consultation;
import com.example.hmspfa.services.ConsultationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ConsultationServiceImpl implements ConsultationService {
    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return null;
    }

    @Override
    public Consultation getConsultationById(Long id) {
        return null;
    }

    @Override
    public void deleteConsultation(Long id) {

    }

    @Override
    public Consultation updateConsultation(Consultation consultation) {
        return null;
    }
}
