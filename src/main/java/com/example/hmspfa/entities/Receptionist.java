package com.example.hmspfa.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("RECEPTIONIST")
public class Receptionist extends User{
    private LocalDate joiningDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "hospital_receptionist",
            joinColumns = @JoinColumn(name = "receptionist_id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id")
    )
    private List<Hospital> hospitals;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "receptionist")
    private List<Invoice> invoices;

    public List<Hospital> getHospitals() {
        if (hospitals == null) {
            hospitals = new ArrayList<Hospital>();
        }
        return hospitals;
    }

}

