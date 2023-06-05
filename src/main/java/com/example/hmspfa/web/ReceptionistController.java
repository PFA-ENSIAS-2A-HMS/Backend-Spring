package com.example.hmspfa.web;

import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.resources.responses.AuthenticationResponse;
import com.example.hmspfa.services.EmailSenderService;
import com.example.hmspfa.services.ReceptionistService;
import com.example.hmspfa.services.implementations.AuthenticationServiceImpl;
import com.example.hmspfa.services.implementations.PasswordGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/receptionists")
@AllArgsConstructor
public class ReceptionistController {
    private final ReceptionistService receptionistService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationServiceImpl authenticationService;
    private final PasswordGeneratorService passwordGeneratorService;
    private final EmailSenderService emailSenderService;
    @PostMapping
    public Receptionist saveReceptionist(@RequestBody Receptionist receptionist) {
        return receptionistService.saveReceptionist(receptionist);
    }
    @PostMapping("/add/{hospitalId}")
    public ResponseEntity<AuthenticationResponse> saveReceptionist(
            @ModelAttribute Receptionist receptionist,
            @PathVariable Long hospitalId,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {
        // Set the default image URL if the imageFile is empty
        if ( imageFile == null || imageFile.isEmpty()) {
            receptionist.setImage_url("images/"+receptionist.getGender()+".jpg");
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
                receptionist.setImage_url(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }

        String password = passwordGeneratorService.generatePassword(10);
        receptionist.setPassword(password);
        emailSenderService.sendEmail(receptionist.getEmail(), "Envoi du mot de passe utilisateur", "Bonjour,\n\nVotre compte a été créé avec succès. Veuillez trouver ci-dessous vos informations de connexion :\n\nEmail : " + receptionist.getEmail() + "\nMot de passe : " + password + "\n\nN'hésitez pas à nous contacter si vous avez des questions ou des préoccupations.\n\nCordialement,\n[Hospital Management System]");
        receptionist.setPassword(passwordEncoder.encode(receptionist.getPassword()));
        receptionist.setPassword(passwordEncoder.encode(receptionist.getPassword()));
        // Save the doctor and associate with the hospital
        receptionist.setPassword(passwordEncoder.encode(receptionist.getPassword()));
        return ResponseEntity.ok(authenticationService.registerReceptionist(receptionist,hospitalId));
    }


    @GetMapping("{id}")
    public Receptionist getReceptionistById(@PathVariable Long id) {
        return receptionistService.getReceptionistById(id);
    }

    @GetMapping
    public List<Receptionist> getAllReceptionists() {
        return receptionistService.getAllReceptionists();
    }

    @PutMapping
    public Receptionist updateReceptionist(@RequestBody Receptionist receptionist) {
        return receptionistService.updateReceptionist(receptionist);
    }

    @DeleteMapping("{id}")
    public void deleteReceptionist(@PathVariable Long id) {
        receptionistService.deleteReceptionist(id);
    }
}
