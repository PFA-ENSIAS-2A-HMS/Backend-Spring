package com.example.hmspfa.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
   @OneToOne
   private Hospital hospital;
   public void setHospital(Hospital hospital) {
      this.hospital = hospital;
   }
}
