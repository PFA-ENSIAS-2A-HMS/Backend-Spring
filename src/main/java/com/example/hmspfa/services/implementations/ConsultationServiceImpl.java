package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Consultation;
import com.example.hmspfa.exceptions.ConsultationNotFoundException;
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
    public Consultation getConsultationById(Long id) throws ConsultationNotFoundException {
        return consultationRepository.findById(id).orElseThrow(
                () -> new ConsultationNotFoundException("Consultation Not Found")
        );
    }

    @Override
    public void deleteConsultation(Long id) throws ConsultationNotFoundException{
        Consultation consultation = consultationRepository.findById(id).orElseThrow(
                () -> new ConsultationNotFoundException("Consultation Not Found")
        );
        consultationRepository.deleteById(id);
    }

    @Override
    public Consultation updateConsultation(Consultation consultation) throws ConsultationNotFoundException {
        Consultation found = consultationRepository.findById(consultation.getId()).orElseThrow(
                () -> new ConsultationNotFoundException("Consultation Not Found")
        );
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }
}
