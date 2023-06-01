package com.example.hmspfa.web;

import com.example.hmspfa.entities.DashboardInfo;
import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.exceptions.HospitalNotFoundException;
import com.example.hmspfa.services.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospitals")
@AllArgsConstructor
public class HospitalController {

    private HospitalService hospitalService;



    @GetMapping("/all")
    List<Hospital> getAllHospitals(){
        return hospitalService.getAllHospitals();
    }
    @GetMapping("/dashboard/{hospitalId}")
    public ResponseEntity<Object> getDashboardInfo(@PathVariable Long hospitalId) {
        try {
            DashboardInfo dashboardInfo = hospitalService.getDashboardInfo(hospitalId);
            return ResponseEntity.ok(dashboardInfo);
        } catch (HospitalNotFoundException e) {
            String message = "Hospital not found for ID: " + hospitalId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + message + "\"}");
        }
    }


    @PostMapping("/{userId}")
    public ResponseEntity<Object> saveHospital(@RequestBody Hospital hospital,@PathVariable Long userId) {
        Hospital savedHospital = hospitalService.saveHospital(hospital,userId);
        if(hospital==null){
            String message = "une erreur est surveune";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + message + "\"}");
        }else{
            return ResponseEntity.ok(savedHospital);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHospitalById(@PathVariable Long id) throws HospitalNotFoundException {
        ResponseEntity<Object> response;
        Hospital hospital = hospitalService.getHospitalById(id);
        if (hospital != null) {
            response = ResponseEntity.ok(hospital);
        } else {
            String message = "No hospital found for ID: " + id;
            response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + message + "\"}");
        }
        return response;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable Long id, @RequestBody Hospital hospital) {
        Hospital updatedHospital = hospitalService.updateHospital(hospital);
        if (updatedHospital != null) {
            return ResponseEntity.ok(updatedHospital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHospital(@PathVariable Long id) {
        Hospital deletedHospital = hospitalService.getHospitalById(id);
        if (deletedHospital != null) {
            hospitalService.deleteHospital(id);
            String message = "Hospital with ID " + id + " has been deleted.";
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + message + "\"}");
        } else {
            String message = "Hospital with ID " + id + " was not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + message + "\"}");
        }
    }
}
