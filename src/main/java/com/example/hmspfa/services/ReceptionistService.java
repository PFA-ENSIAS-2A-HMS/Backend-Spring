package com.example.hmspfa.services;

import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.exceptions.ReceptionistNotFoundException;

import java.util.List;

public interface ReceptionistService {
    Receptionist saveReceptionist(Receptionist receptionist);

    Receptionist saveReceptionist(Receptionist receptionist, Long hospitalId);

    Receptionist getReceptionistById(Long id) throws ReceptionistNotFoundException;
    void deleteReceptionist(Long id) throws ReceptionistNotFoundException;
    Receptionist updateReceptionist(Receptionist receptionist) throws ReceptionistNotFoundException;

    List<Receptionist> getAllReceptionists();
}
