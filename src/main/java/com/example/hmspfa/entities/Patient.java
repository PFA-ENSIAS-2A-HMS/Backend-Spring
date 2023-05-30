package com.example.hmspfa.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PATIENT")
public class Patient extends User{
   private String emergencyContact;
   private String MedicalHistory;
   @OneToMany(mappedBy = "patient")
   private List<Appointment> appointments;

   @ManyToOne
   private Hospital hospital;

   @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   private List<MedicalRecord> medicalRecords;

   @ManyToMany
   private List<Room> rooms;


}
