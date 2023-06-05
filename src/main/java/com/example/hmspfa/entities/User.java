package com.example.hmspfa.entities;

import com.example.hmspfa.enums.Gender;
import com.example.hmspfa.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="User_Role",length = 50,discriminatorType = DiscriminatorType.STRING)
public  class User implements UserDetails {
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return role.getAuthorities(); //ADDED THIS
          return null;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String getPassword() {
        return password;
    }

}
