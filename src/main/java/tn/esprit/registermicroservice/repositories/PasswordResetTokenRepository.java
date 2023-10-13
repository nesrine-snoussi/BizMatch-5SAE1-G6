package tn.esprit.registermicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.registermicroservice.entities.PasswordResetToken;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}

