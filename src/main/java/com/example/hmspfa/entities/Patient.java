package com.example.hmspfa.entities;

import com.example.hmspfa.enums.PatientStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PATIENT")
public class Patient extends User{
   private String cin;
   private String emergencyContact;
   private String MedicalHistory;
   private String address;
   private String bloodType;
   @OneToOne(cascade = CascadeType.ALL,mappedBy = "patient")
   private BiometricData biometricData;
   @Enumerated(EnumType.STRING)
   private PatientStatus status;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "patient")
   private List<Appointment> appointments;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
           name = "hospital_patient",
           joinColumns = @JoinColumn(name = "patient_id"),
           inverseJoinColumns = @JoinColumn(name = "hospital_id")
   )
   private List<Hospital> hospitals;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToOne
   private SuperAdmin superAdmin;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   private List<MedicalRecord> medicalRecords;
/*
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToMany
   private List<Room> rooms;*/
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "patient")
   private List<PatientRoomAssignment> patientAssignments;

   public List<Hospital> getHospitals() {
      if (hospitals == null) {
         hospitals = new ArrayList<Hospital>();
      }
      return hospitals;
   }



}
