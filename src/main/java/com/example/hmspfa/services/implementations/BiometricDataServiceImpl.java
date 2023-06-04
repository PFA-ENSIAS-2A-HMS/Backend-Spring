package com.example.hmspfa.services.implementations;
import com.example.hmspfa.entities.BiometricData;
import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.exceptions.BiometricDataNotFoundException;
import com.example.hmspfa.exceptions.PatientNotFoundException;
import com.example.hmspfa.repositories.BiometricDataRepository;
import com.example.hmspfa.repositories.PatientRepository;
import com.example.hmspfa.services.BiometricDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BiometricDataServiceImpl implements BiometricDataService {

    private final BiometricDataRepository biometricDataRepository;
    private final PatientRepository patientRepository;
    @Override
    public BiometricData saveBiometricData(BiometricData biometricData, Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(!patientOptional.isPresent()){
            throw new PatientNotFoundException("Patient Not Found ");
        }else{
            Patient patient = patientOptional.get();
            biometricData.setPatient(patient);
        }
        return biometricDataRepository.save(biometricData);
    }

    @Override
    public BiometricData getBiometricDataById(Long id) throws BiometricDataNotFoundException {
        // Implémentation de la logique de récupération de la donnée biométrique par ID
        return biometricDataRepository.findById(id)
                .orElseThrow(() -> new BiometricDataNotFoundException("Donnée biométrique introuvable avec l'ID : " + id));
    }

    @Override
    public void deleteBiometricData(Long id) throws BiometricDataNotFoundException {
        // Implémentation de la logique de suppression de la donnée biométrique par ID
        BiometricData biometricData = getBiometricDataById(id);
        biometricDataRepository.delete(biometricData);
    }

    @Override
    public BiometricData updateBiometricData(BiometricData biometricData) throws BiometricDataNotFoundException {
        // Implémentation de la logique de mise à jour de la donnée biométrique
        BiometricData existingBiometricData = getBiometricDataById(biometricData.getId());

       // Vérifier que les getters ne retournent pas de valeurs null avant de les passer aux setters
        if (biometricData.getWeight() != null) {
            existingBiometricData.setWeight(biometricData.getWeight());
        }
        if (biometricData.getHeight() != null) {
            existingBiometricData.setHeight(biometricData.getHeight());
        }
        if (biometricData.getTemperature() != null) {
            existingBiometricData.setTemperature(biometricData.getTemperature());
        }
        if (biometricData.getBloodPressure() != null) {
            existingBiometricData.setBloodPressure(biometricData.getBloodPressure());
        }
        if (biometricData.getPulse() != null) {
            existingBiometricData.setPulse(biometricData.getPulse());
        }
        if (biometricData.getBloodSugarLevel() != null) {
            existingBiometricData.setBloodSugarLevel(biometricData.getBloodSugarLevel());
        }
        if (biometricData.getPatient() != null) {
            existingBiometricData.setPatient(biometricData.getPatient());
        }

        return biometricDataRepository.save(existingBiometricData);
    }

    @Override
    public List<BiometricData> getAllBiometricData() {

        return biometricDataRepository.findAll();
    }

    @Override
    public BiometricData getBiometricDataByPatient(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        Patient patient;
        if(!patientOptional.isPresent()){
            throw new PatientNotFoundException("Patient Not Found");
        }else{
             patient = patientOptional.get();
        }
        return biometricDataRepository.findByPatient(patient);
    }

    @Override
    public BiometricData saveBiometricDataWithPhone(BiometricData biometricData, String phone) {
       Patient patient = patientRepository.findByPhoneNumber(phone);
        if(patient==null){
            throw new PatientNotFoundException("Patient Not Found ");
        }else{
            biometricData.setPatient(patient);
        }
        return biometricDataRepository.save(biometricData);
    }

    @Override
    public BiometricData getBiometricDataByPhone(String phone) {
        Patient patient = patientRepository.findByPhoneNumber(phone);
        if(patient==null){
            throw new PatientNotFoundException("Patient Not Found ");
        }
        // Implémentation de la logique de récupération de la donnée biométrique par ID
        return biometricDataRepository.findByPatient(patient);
    }

    @Override
    public BiometricData updateBiometricDataByPhone(BiometricData biometricData, String phone) {
        BiometricData biometricData1 = this.getBiometricDataByPhone(phone);
        biometricData.setId(biometricData1.getId());
        return this.updateBiometricData(biometricData);
    }

}
