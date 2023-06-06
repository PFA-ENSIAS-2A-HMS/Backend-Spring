package com.example.hmspfa.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User{
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @ManyToOne
   private SuperAdmin superAdmin;
   @OneToOne
   private Hospital hospital;
   public void setHospital(Hospital hospital) {
      this.hospital = hospital;
   }
}
