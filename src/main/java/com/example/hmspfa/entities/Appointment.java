package com.example.hmspfa.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    private Long id;
    private Double fees;
    private LocalDate date;

    private LocalTime time;

    private String reason;

    private String additionalInfos;
    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @OneToMany(mappedBy = "appointment")
    private List<Invoice> invoice;
}
