package com.example.hmspfa.web;

import com.example.hmspfa.entities.BiometricData;
import com.example.hmspfa.exceptions.BiometricDataNotFoundException;
import com.example.hmspfa.services.BiometricDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/biometrics")
@AllArgsConstructor
public class BiometricDataController {

    private final BiometricDataService biometricDataService;

    @PostMapping("/{patientId}")
    public ResponseEntity<BiometricData> saveBiometricData(@RequestBody BiometricData biometricData,
                                                           @PathVariable Long patientId) {

        BiometricData savedBiometricData = biometricDataService.saveBiometricData(biometricData,patientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBiometricData);
    }
    @PostMapping("phone/{phone}")
    public ResponseEntity<BiometricData> saveBiometricDataWithPhoneNumber(@RequestBody BiometricData biometricData,
                                                           @PathVariable String phone) {

        BiometricData savedBiometricData = biometricDataService.saveBiometricDataWithPhone(biometricData,phone);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBiometricData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BiometricData> getBiometricDataById(@PathVariable Long id) {
        try {
            BiometricData biometricData = biometricDataService.getBiometricDataById(id);
            return ResponseEntity.ok(biometricData);
        } catch (BiometricDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("phone/{phone}")
    public ResponseEntity<BiometricData> getBiometricDataByPhone(@PathVariable String phone) {
        try {
            BiometricData biometricData = biometricDataService.getBiometricDataByPhone(phone);
            return ResponseEntity.ok(biometricData);
        } catch (BiometricDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("patient/{id}")
    public ResponseEntity<BiometricData> getBiometricDataByPatientId(@PathVariable Long id) {
        try {
            BiometricData biometricData = biometricDataService.getBiometricDataByPatient(id);
            return ResponseEntity.ok(biometricData);
        } catch (BiometricDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBiometricData(@PathVariable Long id) {
        try {
            biometricDataService.deleteBiometricData(id);
            return ResponseEntity.noContent().build();
        } catch (BiometricDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BiometricData> updateBiometricData(@PathVariable Long id, @RequestBody BiometricData biometricData) {
        try {
            biometricData.setId(id);
            BiometricData updatedBiometricData = biometricDataService.updateBiometricData(biometricData);
            return ResponseEntity.ok(updatedBiometricData);
        } catch (BiometricDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("phone/{phone}")
    public ResponseEntity<BiometricData> updateBiometricDataByPhone(@PathVariable String phone, @RequestBody BiometricData biometricData) {
        try {
            BiometricData updatedBiometricData = biometricDataService.updateBiometricDataByPhone(biometricData,phone);
            return ResponseEntity.ok(updatedBiometricData);
        } catch (BiometricDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BiometricData>> getAllBiometricData() {
        List<BiometricData> biometricDataList = biometricDataService.getAllBiometricData();
        return ResponseEntity.ok(biometricDataList);
    }
}
