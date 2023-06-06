package com.example.hmspfa.web;

import com.example.hmspfa.entities.*;
import com.example.hmspfa.enums.Role;
import com.example.hmspfa.repositories.AdminRepository;
import com.example.hmspfa.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final ReceptionistService receptionistService;

    private final AdminService adminService;

    @GetMapping("/hospital/{id}")
    public ResponseEntity<Hospital> getUserHospitalById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        Role role = user.getRole();
        Hospital hospital = null;
        if(role==Role.DOCTOR){
            Doctor doctor = doctorService.getDoctorById(id);
            hospital = doctor.getHospitals().get(0);
        }else if(role==Role.ADMIN){
           Admin admin = adminService.getAdminById(id);
           hospital = admin.getHospital();
            System.out.println("=======>"+hospital.getId());
        }else if(role==Role.PATIENT){
            Patient patient = patientService.getPatientById(id);
            hospital= patient.getHospitals().get(0);
        }else if(role==Role.RECEPTIONIST){
            Receptionist receptionist = receptionistService.getReceptionistById(id);
            hospital = receptionist.getHospitals().get(0);
        }else if(role==Role.SUPER_ADMIN){

        }

        if (hospital != null) {
            return new ResponseEntity<>(hospital, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
