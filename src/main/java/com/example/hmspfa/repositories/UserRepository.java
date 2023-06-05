package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAll();

    User save(User user);

    void deleteByEmail(String email);

    Optional<User> findById(Long id) ;

    Optional<User> findByEmail(String email);
}
