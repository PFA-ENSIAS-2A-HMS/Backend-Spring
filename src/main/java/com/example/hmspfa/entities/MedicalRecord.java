package com.example.hmspfa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    private Patient patient;

    @OneToMany(mappedBy = "medicalRecord",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Consultation> consultations;
}
