package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Consultation;
import com.example.hmspfa.repositories.ConsultationRepository;
import com.example.hmspfa.services.ConsultationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation getConsultationById(Long id) {
        return consultationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public Consultation updateConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }
}
