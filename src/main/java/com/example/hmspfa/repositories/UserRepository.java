package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
