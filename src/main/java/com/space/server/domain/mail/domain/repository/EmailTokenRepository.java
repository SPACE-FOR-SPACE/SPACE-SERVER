package com.space.server.domain.mail.domain.repository;

import com.space.server.domain.mail.domain.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {
    EmailToken findByToken(String token);
    EmailToken findByEmailAndIsVerifiedFalse(String email);
    void deleteByEmail(String email);
    EmailToken findByEmailAndIsVerified(String email, boolean verified);
    void deleteByExpiryDateBefore(LocalDateTime now);
}