package com.example.hmspfa.entities;
import com.example.hmspfa.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "appointment")
    private List<Invoice> invoice;

    private AppointmentStatus status;
}
