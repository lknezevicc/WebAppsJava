package hr.tvz.knezevic.njamapp.repository;

import hr.tvz.knezevic.njamapp.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Query("""
        SELECT t FROM RefreshToken t WHERE t.user.username = :username
    """)
    Optional<RefreshToken> findByUsername(String username);
}
