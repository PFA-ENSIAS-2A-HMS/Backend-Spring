package com.example.hmspfa.entities;

import com.example.hmspfa.enums.HospitalStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank
   private String name;
   private String logo;

   @NotBlank
   private String address;
   private HospitalStatus status;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToMany(mappedBy = "hospitals", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Doctor> doctors;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToMany(mappedBy = "hospitals", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Patient> patients;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToMany(mappedBy = "hospitals", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Receptionist> receptionists;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Room> rooms;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Appointment> appointments;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @OneToOne(mappedBy = "hospital")
   private Admin admin;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToOne
   private SuperAdmin superAdmin;
   public void setAdmin(Admin admin) {
      this.admin = admin;
      admin.setHospital(this);
   }

   public List<Doctor> getDoctors() {
      Set<Doctor> uniqueDoctors = new HashSet<>();
      for (Doctor doctor : doctors) {
         uniqueDoctors.add(doctor);
      }
      return new ArrayList<>(uniqueDoctors);
   }

   public List<Patient> getPatients() {
      Set<Patient> uniquePatients = new HashSet<>();
      for (Patient patient : patients) {
         uniquePatients.add(patient);
      }
      return new ArrayList<>(uniquePatients);
   }
   public List<Receptionist> getReceptionists() {
      Set<Receptionist> uniqueReceptionists = new HashSet<>();
      for (Receptionist receptionist : receptionists) {
         uniqueReceptionists.add(receptionist);
      }
      return new ArrayList<>(uniqueReceptionists);
   }

   public List<Room> getRooms(){
      Set<Room> uniqueRooms= new HashSet<>();
      for (Room room : rooms) {
         uniqueRooms.add(room);
      }
      return new ArrayList<>(uniqueRooms);
   }

   public List<Appointment> getAppointments(){
      Set<Appointment> uniqueAppointments= new HashSet<>();
      for (Appointment app : appointments) {
         uniqueAppointments.add(app);
      }
      return new ArrayList<>(uniqueAppointments);
   }

}
