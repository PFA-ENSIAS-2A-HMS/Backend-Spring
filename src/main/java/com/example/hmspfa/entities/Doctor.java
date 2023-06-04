package com.example.hmspfa.entities;

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
@DiscriminatorValue("DOCTOR")
public class Doctor extends User {
  private String speciality;
  private String location;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
  private List<Appointment> appointments;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "hospital_doctor",
          joinColumns = @JoinColumn(name = "doctor_id"),
          inverseJoinColumns = @JoinColumn(name = "hospital_id")
  )
  private List<Hospital> hospitals;

  public List<Hospital> getHospitals() {
    if (hospitals == null) {
      hospitals = new ArrayList<Hospital>();
    }
    return hospitals;
  }



}
