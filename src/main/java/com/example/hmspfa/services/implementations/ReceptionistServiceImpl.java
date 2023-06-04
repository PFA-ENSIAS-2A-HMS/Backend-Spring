package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.exceptions.HospitalNotFoundException;
import com.example.hmspfa.exceptions.ReceptionistNotFoundException;
import com.example.hmspfa.repositories.HospitalRepository;
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
    private final HospitalRepository hospitalRepository;

    @Override
    public Receptionist saveReceptionist(Receptionist receptionist) {
        return receptionistRepository.save(receptionist);
    }

    @Override
    public Receptionist saveReceptionist(Receptionist receptionist, Long hospitalId) {
        try {
            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new HospitalNotFoundException("Hospital with ID " + hospitalId + " not found."));

            receptionist.getHospitals().add(hospital);
            return receptionistRepository.save(receptionist);
        } catch (Exception e) {
            log.error("Error while saving the receptionist: {}", e.getMessage());
            throw new RuntimeException("Error while saving the receptionist", e);
        }
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
