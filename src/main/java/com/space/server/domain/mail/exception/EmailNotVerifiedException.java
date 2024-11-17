package com.space.server.domain.mail.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class EmailNotVerifiedException extends SpaceException {
    public EmailNotVerifiedException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_NOT_VERIFIED", "이메일 인증이 필요합니다.");
    }
}
