package com.example.hmspfa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomNumber;
    private int roomCapacity;
    private int Floor;
    private int occupiedBeds;
    @ManyToOne
    private Hospital hospital;

    @ManyToMany(mappedBy = "rooms")
    private List<Patient> patients;

}
