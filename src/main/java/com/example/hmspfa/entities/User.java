package com.example.hmspfa.entities;

import com.example.hmspfa.enums.Gender;
import com.example.hmspfa.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="User_Role",length = 50,discriminatorType = DiscriminatorType.STRING)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String phoneNumber;
    private String email;
    private String lastName;
    private LocalDate date_of_birth;
    private String image_url;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
