package com.example.hmspfa.web;

import com.example.hmspfa.entities.Appointment;
import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.enums.AppointmentStatus;
import com.example.hmspfa.services.AppointmentService;
import com.example.hmspfa.services.DoctorService;
import com.example.hmspfa.services.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        Appointment createdAppointment = appointmentService.saveAppointment(appointment);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PostMapping("/hospital/{hospitalId}")
    public ResponseEntity<Appointment> createAppointmentForHospital(@RequestBody Appointment appointment,
                                                                    @PathVariable Long hospitalId) {
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        appointment.setHospital(hospital);
        Appointment createdAppointment = appointmentService.saveAppointment(appointment);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Patient> getPatientByDoctor(@PathVariable Long doctorId){
        List<Patient> patients = new ArrayList<>();
        Doctor doctor = doctorService.getDoctorById(doctorId);
        List<Appointment> appointments = appointmentService.getAppointmentByDoctor(doctor);
        for (Appointment appointment : appointments) {
           patients.add(appointment.getPatient());
        }
        return patients;
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<Appointment> getPatientByHospital(@PathVariable Long hospitalId){
        List<Patient> patients = new ArrayList<>();
        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        List<Appointment> appointments = appointmentService.getAppointmentByHospital(hospital);
        return appointments;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        appointment.setId(id);
        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
        if (updatedAppointment != null) {
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
