package com.example.hmspfa.services;

import com.example.hmspfa.entities.Receptionist;

public interface ReceptionistService {
    Receptionist saveReceptionist(Receptionist receptionist);
    Receptionist getReceptionistById(Long id);
    void deleteReceptionist(Long id);
    Receptionist updateReceptionist(Receptionist receptionist);
}
