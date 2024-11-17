package com.space.server.domain.mail.exception;

import com.space.server.common.exception.mail.SpaceMailException;
import org.springframework.http.HttpStatus;

public class EmailNotVerifiedException extends SpaceMailException {
    public EmailNotVerifiedException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_NOT_VERIFIED", "이메일 인증이 필요합니다.");
    }
}
