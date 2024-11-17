package com.space.server.domain.mail.exception;

import com.space.server.common.exception.mail.SpaceMailException;
import org.springframework.http.HttpStatus;

public class ExpiredEmailTokenException extends SpaceMailException {
    public ExpiredEmailTokenException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_TOKEN_EXPIRED", "만료된 이메일 토큰입니다.");
    }
}
