package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.exceptions.HospitalNotFoundException;
import com.example.hmspfa.exceptions.PatientNotFoundException;
import com.example.hmspfa.repositories.HospitalRepository;
import com.example.hmspfa.repositories.PatientRepository;
import com.example.hmspfa.services.PatientService;
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
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public Patient savePatient(Patient patient, Long hospitalId) {
        try {
            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new HospitalNotFoundException("Hospital with ID " + hospitalId + " not found."));

            patient.getHospitals().add(hospital);
            return patientRepository.save(patient);
        } catch (Exception e) {
            log.error("Error while saving the patient: {}", e.getMessage());
            throw new RuntimeException("Error while saving the patient", e);
        }
    }

    @Override
    public Patient getPatientById(Long id) throws PatientNotFoundException {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient Not Found"));
    }

    @Override
    public void deletePatient(Long id) throws PatientNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if (patientOptional.isEmpty()) {
            throw new PatientNotFoundException("Patient Not Found");
        }

        patientRepository.deleteById(id);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findById(patient.getId());

        if (patientOptional.isEmpty()) {
            throw new PatientNotFoundException("Patient Not Found");
        }
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

}
