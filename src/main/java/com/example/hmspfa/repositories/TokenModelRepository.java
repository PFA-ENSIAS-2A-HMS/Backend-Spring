package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.TokenModel;
import com.example.hmspfa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenModelRepository extends JpaRepository<TokenModel,Long> {
    void deleteByUser(User user);
}
