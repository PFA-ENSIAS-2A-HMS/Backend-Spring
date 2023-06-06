package com.example.hmspfa.services.implementations;

import com.example.hmspfa.config.JwtService;
import com.example.hmspfa.entities.*;
import com.example.hmspfa.enums.Role;
import com.example.hmspfa.repositories.TokenRepository;
import com.example.hmspfa.repositories.UserRepository;
import com.example.hmspfa.resources.RequestModels.AuthenticationRequest;
import com.example.hmspfa.resources.responses.AuthenticationResponse;
import com.example.hmspfa.services.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;
    private final AdminServiceImpl adminService;

    private final PatientServiceImpl patientService;
    private final DoctorServiceImpl doctorService;
    private final  ReceptionistServiceImpl receptionistService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse registerAdmin(Admin request){
        request.setRole(Role.ADMIN);
        Admin admin  = adminService.saveAdmin(request);

        var jwtToken = jwtService.generateToken((UserDetails) admin);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) admin);

        saveUserToken(admin, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role("ADMIN")
                .id(admin.getId())
                .build();
    }

    @Override
    public AuthenticationResponse registerPatient(Patient request,Long hospitalId){
        request.setRole(Role.PATIENT);
        Patient patient  = patientService.savePatient(request,hospitalId);
        var jwtToken = jwtService.generateToken((UserDetails) patient);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) patient);

        saveUserToken(patient, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role("PATIENT")
                .id(patient.getId())
                .build();
    }
    @Override
    public AuthenticationResponse registerReceptionist(Receptionist request,Long hospitalId){
        request.setRole(Role.RECEPTIONIST);
        Receptionist receptionist  = receptionistService.saveReceptionist(request,hospitalId);
        var jwtToken = jwtService.generateToken((UserDetails) receptionist);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) receptionist);

        saveUserToken(receptionist, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role("RECEPTIONIST")
                .id(receptionist.getId())
                .build();
    }

    @Override
    public AuthenticationResponse registerDoctor(Doctor request,Long hospitalId){
        request.setRole(Role.DOCTOR);
        Doctor doctor  = doctorService.saveDoctor(request,hospitalId);
        var jwtToken = jwtService.generateToken((UserDetails) doctor);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) doctor);

        saveUserToken(doctor, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role("DOCTOR")
                .id(doctor.getId())
                .build();
    }
    @Override
    public AuthenticationResponse registerSuperAdmin(SuperAdmin request){
        request.setRole(Role.SUPER_ADMIN);
        var jwtToken = jwtService.generateToken((UserDetails) request);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) request);

        saveUserToken(request, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role("SUPER_ADMIN")
                .id(request.getId())
                .build();
    }
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role(user.getRole().name())
                .id(user.getId())
                .build();

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = TokenModel.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, (UserDetails) user)) {
                var accessToken = jwtService.generateToken((UserDetails) user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
