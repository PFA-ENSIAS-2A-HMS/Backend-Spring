package com.example.hmspfa.web;

import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("api/v1/doctors")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    /*
    @PostMapping("add/{hospitalId}")
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor,@PathVariable Long hospitalId) {
        Doctor savedDoctor = doctorService.saveDoctor(doctor,hospitalId);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }
   */

    @PostMapping("add/{hospitalId}")
    public ResponseEntity<Doctor> saveDoctor(
            @ModelAttribute Doctor doctor,
            @PathVariable Long hospitalId,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {
        // Set the default image URL if the imageFile is empty
        if (imageFile == null || imageFile.isEmpty()) {
            doctor.setImage_url("images/"+doctor.getGender()+".jpg");
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
                doctor.setImage_url(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }

        // Save the doctor and associate with the hospital
        Doctor savedDoctor = doctorService.saveDoctor(doctor, hospitalId);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }



    @GetMapping("download/images/{imageName}")
    public ResponseEntity<InputStreamResource> downloadImage(@PathVariable String imageName) throws IOException {
        // Get the absolute path to the images directory within the project
        String directory = "images/"; // Relative path within the project
        String absolutePath = new File("src/main/resources/" + directory).getAbsolutePath();

        // Load the image file
        File file = new File(absolutePath, imageName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        // Set the Content-Type header based on the image file type
        String contentType = URLConnection.guessContentTypeFromName(file.getName());

        // Return the ResponseEntity with the image file and headers
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(file.length())
                .body(resource);
    }


    @GetMapping("display/images/{imageName}")
    public ResponseEntity<byte[]> displayImage(@PathVariable String imageName) throws IOException {
        // Get the absolute path to the images directory within the project
        String directory = "images/"; // Relative path within the project
        String absolutePath = new File("src/main/resources/" + directory).getAbsolutePath();

        // Load the image file
        File file = new File(absolutePath, imageName);
        byte[] imageBytes = Files.readAllBytes(file.toPath());

        // Set the Content-Type header based on the image file type
        String contentType = URLConnection.guessContentTypeFromName(file.getName());

        // Return the ResponseEntity with the image data and headers
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imageBytes);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor) {
        Doctor existingDoctor = doctorService.getDoctorById(id);
        if (existingDoctor != null) {
            doctor.setId(existingDoctor.getId());
            Doctor updatedDoctor = doctorService.updateDoctor(doctor);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
