package com.space.server.domain.mail.presentation;

import com.space.server.domain.mail.exception.value.EmailVerificationStatus;
import com.space.server.domain.mail.presentation.dto.request.EmailVerificationRequest;
import com.space.server.domain.mail.service.EmailTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@Tag(name = "Email", description = "이메일 인증 API")
public class EmailTokenController {
    private final EmailTokenService emailTokenService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/mails")
    @Operation(summary = "메일 인증", description = "해당 이메일을 인증합니다.")
    public void requestVerification(
            @RequestBody EmailVerificationRequest request) {
        emailTokenService.sendVerificationEmail(request.email());
    }

    @GetMapping("/verify")
    public ModelAndView verifyEmail(@RequestParam String token) {
        emailTokenService.verifyEmail(token);

        ModelAndView modelAndView = new ModelAndView("verification-result");
        modelAndView.addObject("status", EmailVerificationStatus.SUCCESS.name());

        return modelAndView;
    }
}
