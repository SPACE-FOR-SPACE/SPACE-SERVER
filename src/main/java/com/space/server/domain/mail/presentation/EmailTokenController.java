package com.space.server.domain.mail.presentation;

import com.space.server.domain.mail.presentation.dto.request.EmailVerificationRequest;
import com.space.server.domain.mail.service.EmailTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmailTokenController {
    private final EmailTokenService emailTokenService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/verify-request")
    public void requestVerification(
            @RequestBody EmailVerificationRequest request) {
        emailTokenService.sendVerificationEmail(request.email());
    }

    @GetMapping("/verify")
    public ResponseEntity<Void> verifyEmail(@RequestParam String token) {
        emailTokenService.verifyEmail(token);
        return ResponseEntity.ok().build();
    }
}
