package com.example.hmspfa.web;

import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.enums.PatientStatus;
import com.example.hmspfa.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/{hospitalId}")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient,@PathVariable Long hospitalId) {
        Patient savedPatient = patientService.savePatient(patient,hospitalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @PostMapping("add/{hospitalId}")
    public ResponseEntity<Patient> savePatient(
            @ModelAttribute Patient patient,
            @PathVariable Long hospitalId,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {
        // Set the default image URL if the imageFile is empty
        if (imageFile == null || imageFile.isEmpty()) {
            patient.setImage_url("images/"+patient.getGender()+".jpg");
        } else {
            // Process the image file
            try {
                String fileName = imageFile.getOriginalFilename();
                String directory = "images/"; // Relative path within the project
                String imageUrl = directory + fileName; // Set the path where you want to store the image

                // Get the absolute path to the images directory within the project
                String absolutePath = new File("src/main/resources/" + directory).getAbsolutePath();

                // Create the images directory if it doesn't exist
                File imageDir = new File(absolutePath);
                if (!imageDir.exists()) {
                    imageDir.mkdirs();
                }

                // Save the image file to the server
                File image = new File(imageDir, fileName);
                imageFile.transferTo(image);
                patient.setImage_url(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }

        // Save the patient and associate with the hospital
        patient.setStatus(PatientStatus.PENDING);
        Patient savedPatient = patientService.savePatient(patient, hospitalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("phone/{phoneNumber}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String phoneNumber) {
        Patient patient = patientService.getPatientByPhoneNumber(phoneNumber);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient != null) {
            patient.setId(existingPatient.getId());
            if (patient.getFirstName() != null) {
                existingPatient.setFirstName(patient.getFirstName());
            }
            if (patient.getLastName() != null) {
                existingPatient.setLastName(patient.getLastName());
            }
            if (patient.getImage_url() != null) {
                existingPatient.setImage_url(patient.getImage_url());
            }
            if (patient.getEmail() != null) {
                existingPatient.setEmail(patient.getEmail());
            }
            if (patient.getGender() != null) {
                existingPatient.setGender(patient.getGender());
            }
            if (patient.getDate_of_birth() != null) {
                existingPatient.setDate_of_birth(patient.getDate_of_birth());
            }
            if (patient.getCin() != null) {
                existingPatient.setCin(patient.getCin());
            }
            if (patient.getEmergencyContact() != null) {
                existingPatient.setEmergencyContact(patient.getEmergencyContact());
            }
            if (patient.getMedicalHistory() != null) {
                existingPatient.setMedicalHistory(patient.getMedicalHistory());
            }
            if (patient.getAddress() != null) {
                existingPatient.setAddress(patient.getAddress());
            }
            if (patient.getBloodType() != null) {
                existingPatient.setBloodType(patient.getBloodType());
            }
            if (patient.getStatus() != null) {
                existingPatient.setStatus(patient.getStatus());
            }

            if(patient.getPhoneNumber()!=null){
                existingPatient.setPhoneNumber(patient.getPhoneNumber());
            }


            Patient updatedPatient = patientService.updatePatient(existingPatient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
}
