package com.example.hmspfa.services;

import com.example.hmspfa.entities.BiometricData;
import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.exceptions.BiometricDataNotFoundException;

import java.util.List;


public interface BiometricDataService {

    BiometricData saveBiometricData(BiometricData biometricData, Long patientId);

    BiometricData getBiometricDataById(Long id) throws BiometricDataNotFoundException;

    void deleteBiometricData(Long id) throws BiometricDataNotFoundException;

    BiometricData updateBiometricData(BiometricData biometricData) throws BiometricDataNotFoundException;

    List<BiometricData> getAllBiometricData();

    public BiometricData getBiometricDataByPatient(Long patientId);

    BiometricData saveBiometricDataWithPhone(BiometricData biometricData, String phone);

    BiometricData getBiometricDataByPhone(String phone);

    BiometricData updateBiometricDataByPhone(BiometricData biometricData, String phone);
}