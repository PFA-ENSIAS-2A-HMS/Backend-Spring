package com.example.hmspfa.web;

import com.example.hmspfa.entities.Admin;
import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.enums.PatientStatus;
import com.example.hmspfa.resources.RequestModels.AuthenticationRequest;
import com.example.hmspfa.resources.responses.AuthenticationResponse;
import com.example.hmspfa.services.PatientService;
import com.example.hmspfa.services.implementations.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final PatientService patientService;

    @PostMapping("/register/receptionist/{hospitalId}")
    public ResponseEntity<AuthenticationResponse> registerReceptionist(@RequestBody Receptionist request,
                                                                       @PathVariable Long hospitalId,
                                                                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile)throws IOException{
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if (imageFile == null || imageFile.isEmpty()) {
            request.setImage_url("images/"+request.getGender()+".jpg");
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
                request.setImage_url(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
        return ResponseEntity.ok(authenticationService.registerReceptionist(request,hospitalId));
    }
    @PostMapping("/register/doctor/{hospitalId}")
    public ResponseEntity<AuthenticationResponse> registerDoctor(@RequestBody Doctor request,
                                                                 @PathVariable Long hospitalId,
                                                                 @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException{
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if (imageFile == null || imageFile.isEmpty()) {
            request.setImage_url("images/"+request.getGender()+".jpg");
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
                request.setImage_url(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
        // Save the patient and associate with the hospital
        return ResponseEntity.ok(authenticationService.registerDoctor(request,hospitalId));
    }
    @PostMapping("/register/patient/{hospitalId}")
    public ResponseEntity<AuthenticationResponse> registerPatient(@RequestBody Patient request,
                                                                  @PathVariable Long hospitalId,
                                                                  @RequestParam(value = "imageFile", required = false) MultipartFile imageFile)throws IOException{
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if (imageFile == null || imageFile.isEmpty()) {
            request.setImage_url("images/"+request.getGender()+".jpg");
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
                request.setImage_url(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
        request.setEmail(request.getPhoneNumber());
        // Save the patient and associate with the hospital
        request.setStatus(PatientStatus.PENDING);
        return ResponseEntity.ok(authenticationService.registerPatient(request,hospitalId));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody Admin request,
                                                                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException{
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if (imageFile == null || imageFile.isEmpty()) {
            request.setImage_url("images/"+request.getGender()+".jpg");
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
                request.setImage_url(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }


}
