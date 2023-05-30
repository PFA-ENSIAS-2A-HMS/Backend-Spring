package com.example.hmspfa.entities;

import com.example.hmspfa.enums.InvoiceStatus;
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
    private Long id;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private String itemsServices;
    private Double Taxes;
    private Double discounts;
    private String notes;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    @ManyToOne
    private Appointment appointment;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Receptionist receptionist;
}
