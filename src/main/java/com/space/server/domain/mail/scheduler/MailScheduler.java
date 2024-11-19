package com.space.server.domain.mail.scheduler;

import com.space.server.domain.mail.domain.repository.EmailTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class MailScheduler {

    private final EmailTokenRepository emailTokenRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupExpiredTokens() {
        emailTokenRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }
}
