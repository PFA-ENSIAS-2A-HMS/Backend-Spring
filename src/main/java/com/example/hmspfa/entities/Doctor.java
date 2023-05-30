package com.example.hmspfa.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("DOCTOR")
public class Doctor extends User{
  private String speciality;
  private String location;
  @OneToMany(mappedBy = "doctor")
  private List<Appointment> appointments;
  @ManyToOne
  private  Hospital hospital;
}
