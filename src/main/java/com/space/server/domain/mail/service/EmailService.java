package com.space.server.domain.mail.service;

import com.space.server.domain.mail.exception.EmailSendFailedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${server.proxy.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendVerificationEmail(String to, String token) {
        try {
            Context context = new Context();
            context.setVariable("verificationLink", serverUrl + "/verify?token=" + token);

            String process = templateEngine.process("verification-mail", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setSubject("이메일 주소 확인");
            helper.setText(process, true);

            mailSender.send(mimeMessage);
            log.info("Verification email sent to: {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send email to: {}", to, e);
            throw new EmailSendFailedException();
        }
    }
}