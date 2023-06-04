package com.example.hmspfa.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class PatientRoomAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String additionalNotes;

}