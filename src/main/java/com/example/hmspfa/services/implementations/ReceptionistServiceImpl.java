package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.repositories.ReceptionistRepository;
import com.example.hmspfa.services.ReceptionistService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ReceptionistServiceImpl implements ReceptionistService {

    private final ReceptionistRepository receptionistRepository;

    @Override
    public Receptionist saveReceptionist(Receptionist receptionist) {
        return receptionistRepository.save(receptionist);
    }

    @Override
    public Receptionist getReceptionistById(Long id) {
        return receptionistRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteReceptionist(Long id) {
        receptionistRepository.deleteById(id);
    }

    @Override
    public Receptionist updateReceptionist(Receptionist receptionist) {
        return receptionistRepository.save(receptionist);
    }

    @Override
    public List<Receptionist> getAllReceptionists() {
        return receptionistRepository.findAll();
    }
}
