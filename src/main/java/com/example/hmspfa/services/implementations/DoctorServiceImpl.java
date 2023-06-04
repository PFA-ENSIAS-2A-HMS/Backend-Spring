package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.exceptions.DoctorNotFoundException;
import com.example.hmspfa.exceptions.HospitalNotFoundException;
import com.example.hmspfa.repositories.DoctorRepository;
import com.example.hmspfa.repositories.HospitalRepository;
import com.example.hmspfa.services.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final HospitalServiceImpl hospitalService;

    @Override
    public Doctor saveDoctor(Doctor doctor, Long hospitalId) {
        try {
            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new HospitalNotFoundException("Hospital with ID " + hospitalId + " not found."));

            doctor.getHospitals().add(hospital);
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            log.error("Error while saving the doctor: {}", e.getMessage());
            throw new RuntimeException("Error while saving the doctor", e);
        }
    }


    @Override
    public Doctor getDoctorById(Long id) throws DoctorNotFoundException {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor Not Found")
                );
    }


    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new DoctorNotFoundException("Doctor Not Found")
        );
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor)throws DoctorNotFoundException {
        Doctor found = doctorRepository.findById(doctor.getId()).orElseThrow(
                () -> new DoctorNotFoundException("Doctor Not Found")
        );
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


}
