package com.example.hmspfa.entities;

import com.example.hmspfa.enums.HospitalStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
   @OneToOne(mappedBy = "hospital")
   private Admin admin;

   public void setAdmin(Admin admin) {
      this.admin = admin;
      admin.setHospital(this);
   }
}
