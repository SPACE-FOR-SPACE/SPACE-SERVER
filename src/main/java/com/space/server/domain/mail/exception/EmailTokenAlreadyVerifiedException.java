package com.space.server.domain.mail.exception;

import com.space.server.common.exception.mail.SpaceMailException;
import org.springframework.http.HttpStatus;

public class EmailTokenAlreadyVerifiedException extends SpaceMailException {
    public EmailTokenAlreadyVerifiedException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_TOKEN_ALREADY_VERIFIED", "이미 인증된 토큰입니다.");
    }
}
