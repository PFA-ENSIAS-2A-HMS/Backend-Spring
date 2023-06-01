package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Admin;
import com.example.hmspfa.entities.DashboardInfo;
import com.example.hmspfa.entities.Hospital;

import com.example.hmspfa.enums.HospitalStatus;
import com.example.hmspfa.exceptions.HospitalNotFoundException;
import com.example.hmspfa.exceptions.UserNotFoundException;
import com.example.hmspfa.mappers.HospitalMapperImpl;
import com.example.hmspfa.repositories.AdminRepository;
import com.example.hmspfa.repositories.HospitalRepository;
import com.example.hmspfa.services.HospitalService;
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
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final AdminRepository adminRepository;
    private final HospitalMapperImpl hospitalMapper;
    @Override
    public Hospital saveHospital(Hospital hospital, Long userId) throws UserNotFoundException {
        hospital.setStatus(HospitalStatus.CREATED);
        // Retrieve the user from the database using the userId
        Optional<Admin> adminOptional = adminRepository.findById(userId);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            // Set the admin for the hospital
            hospital.setAdmin(admin);
            // Set the hospital for the admin
            admin.setHospital(hospital);
            // Save the hospital and the admin to the database
            hospitalRepository.save(hospital);
            adminRepository.save(admin);

            return hospital;
        } else {
            // Handle the case when the user is not found
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }


    @Override
    public Hospital getHospitalById(Long id) throws HospitalNotFoundException {
        Optional<Hospital> optionalHospital = Optional.ofNullable(hospitalRepository.findById(id).orElseThrow(
                () -> new HospitalNotFoundException("Hospital Not Found !")
        ));
        return optionalHospital.orElse(null);
    }

    @Override
    public void deleteHospital(Long id) throws HospitalNotFoundException {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if (hospitalOptional.isEmpty()) {
            throw new HospitalNotFoundException("Hospital Not Found");
        }
        hospitalRepository.deleteById(id);
    }


    @Override
    public Hospital updateHospital(Hospital hospital) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospital.getId());
        if (hospitalOptional.isEmpty()) {
            throw new HospitalNotFoundException("Hospital Not Found");
        }
        return hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @Override
    public DashboardInfo getDashboardInfo(Long hospitalId) throws HospitalNotFoundException {
        Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);
            int totalDoctors = hospital.get().getDoctors().size();
            int totalPatients = hospital.get().getPatients().size();
            int totalAppointments = 0; // Logic to calculate total appointments for the hospital
            int totalRooms = hospital.get().getRooms().size();

        return new DashboardInfo(totalDoctors, totalPatients, totalAppointments, totalRooms);
    }

}
