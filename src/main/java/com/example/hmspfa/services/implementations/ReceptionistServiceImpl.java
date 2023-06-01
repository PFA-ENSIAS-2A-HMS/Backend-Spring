package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.exceptions.ReceptionistNotFoundException;
import com.example.hmspfa.repositories.ReceptionistRepository;
import com.example.hmspfa.services.ReceptionistService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Receptionist getReceptionistById(Long id) throws ReceptionistNotFoundException {
        return receptionistRepository.findById(id)
                .orElseThrow(() -> new ReceptionistNotFoundException("Receptionist Not Found"));
    }


    @Override
    public void deleteReceptionist(Long id) throws ReceptionistNotFoundException {
        Optional<Receptionist> receptionistOptional = receptionistRepository.findById(id);

        if (receptionistOptional.isEmpty()) {
            throw new ReceptionistNotFoundException("Receptionist Not Found");
        }

        receptionistRepository.deleteById(id);
    }

    @Override
    public Receptionist updateReceptionist(Receptionist receptionist) {
        Optional<Receptionist> receptionistOptional = receptionistRepository.findById(receptionist.getId());

        if (receptionistOptional.isEmpty()) {
            throw new ReceptionistNotFoundException("Receptionist Not Found");
        }
        return receptionistRepository.save(receptionist);
    }

    @Override
    public List<Receptionist> getAllReceptionists() {
        return receptionistRepository.findAll();
    }
}
