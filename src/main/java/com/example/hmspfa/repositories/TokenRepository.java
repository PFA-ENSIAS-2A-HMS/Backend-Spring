package com.example.hmspfa.repositories;
import com.example.hmspfa.entities.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, Long> {

    @Query(value = """
      select t from TokenModel t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<TokenModel> findAllValidTokenByUser(Long id);

    Optional<TokenModel> findByToken(String token);
}
