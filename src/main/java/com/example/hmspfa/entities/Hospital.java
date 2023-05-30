package com.example.hmspfa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital{
   @Id
   private Long id;
   private String name;
   private String logo;
   private String address;
   @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
   private List<Doctor> doctors;

   @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   private List<Patient> patients;

   @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   private List<Receptionist> receptionists;

   @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   private List<Room> rooms;

   @OneToOne(mappedBy ="hospital")
   private Admin admin;
}
