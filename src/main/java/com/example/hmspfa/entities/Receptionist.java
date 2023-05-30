package com.example.hmspfa.entities;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("RECEPTIONIST")
public class Receptionist extends User{
    private LocalDate joiningDate;
    @ManyToOne
    private Hospital hospital;
    @OneToMany(mappedBy = "receptionist")
    private List<Invoice> invoices;
}

