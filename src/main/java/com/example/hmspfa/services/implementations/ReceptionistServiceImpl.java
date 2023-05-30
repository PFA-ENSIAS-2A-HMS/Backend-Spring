package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.services.ReceptionistService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ReceptionistServiceImpl implements ReceptionistService {
    @Override
    public Receptionist saveReceptionist(Receptionist receptionist) {
        return null;
    }

    @Override
    public Receptionist getReceptionistById(Long id) {
        return null;
    }

    @Override
    public void deleteReceptionist(Long id) {

    }

    @Override
    public Receptionist updateReceptionist(Receptionist receptionist) {
        return null;
    }
}
