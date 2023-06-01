package com.example.hmspfa.entities;

import com.example.hmspfa.enums.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private String itemsServices;
    private Double Taxes;
    private Double discounts;
    private String notes;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Appointment appointment;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Patient patient;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Receptionist receptionist;
}
