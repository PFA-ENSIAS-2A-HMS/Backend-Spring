package com.example.hmspfa.services;

import com.example.hmspfa.entities.Admin;
import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.resources.RequestModels.AuthenticationRequest;
import com.example.hmspfa.resources.responses.AuthenticationResponse;

public interface AuthenticationService {

//    AuthenticationResponse register(RegisterRequest request);


    AuthenticationResponse registerAdmin(Admin request);

    AuthenticationResponse registerPatient(Patient request, Long hospitalId);

    AuthenticationResponse registerReceptionist(Receptionist request, Long hospitalId);

    AuthenticationResponse registerDoctor(Doctor request, Long hospitalId);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}

