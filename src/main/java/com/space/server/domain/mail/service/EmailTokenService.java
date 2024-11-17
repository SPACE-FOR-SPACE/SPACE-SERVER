package com.space.server.domain.mail.service;

import com.space.server.domain.mail.domain.EmailToken;
import com.space.server.domain.mail.domain.repository.EmailTokenRepository;
import com.space.server.domain.mail.exception.EmailTokenAlreadyVerifiedException;
import com.space.server.domain.mail.exception.EmailTokenNotFoundException;
import com.space.server.domain.mail.exception.ExpiredEmailTokenException;
import com.space.server.domain.user.domain.repository.UserRepository;
import com.space.server.domain.user.exception.UserExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailTokenService {
    private final EmailTokenRepository emailTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public void sendVerificationEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserExistedException();
        }

        EmailToken existingToken =
                emailTokenRepository.findByEmailAndIsVerifiedFalse(email);
        if (existingToken != null) {
            emailTokenRepository.delete(existingToken);
        }

        EmailToken emailToken = EmailToken.builder()
                .email(email)
                .build();

        emailTokenRepository.save(emailToken);
        emailService.sendVerificationEmail(email, emailToken.getToken());
    }

    public void verifyEmail(String token) {
        EmailToken emailToken = emailTokenRepository.findByToken(token);
        if (emailToken == null) {
            throw new EmailTokenNotFoundException();
        }

        if (emailToken.isExpired()) {
            throw new ExpiredEmailTokenException();
        }

        if (emailToken.isVerified()) {
            throw new EmailTokenAlreadyVerifiedException();
        }

        emailToken.verify();
    }

    public boolean isEmailVerified(String email) {
        EmailToken emailToken =
                emailTokenRepository.findByEmailAndIsVerified(email, true);
        return emailToken != null;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupExpiredTokens() {
        emailTokenRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }
}