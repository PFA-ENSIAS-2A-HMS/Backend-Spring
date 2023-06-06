package com.example.hmspfa.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("SUPER_ADMIN")
public class SuperAdmin extends User{

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "superAdmin")
    List<Hospital> hospitals;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany
    List<Patient> patients;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany
    List<Doctor> doctors;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany
    List<Room> rooms;


}
