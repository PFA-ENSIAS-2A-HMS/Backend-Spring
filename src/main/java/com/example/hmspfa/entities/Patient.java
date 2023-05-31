package com.example.hmspfa.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
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
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "patient")
   private List<Appointment> appointments;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToOne
   private Hospital hospital;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   private List<MedicalRecord> medicalRecords;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToMany
   private List<Room> rooms;


}
