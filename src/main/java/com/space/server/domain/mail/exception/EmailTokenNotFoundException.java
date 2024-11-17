package com.space.server.domain.mail.exception;

import com.space.server.common.exception.mail.SpaceMailException;
import org.springframework.http.HttpStatus;

public class EmailTokenNotFoundException extends SpaceMailException {
    public EmailTokenNotFoundException() {
      super(HttpStatus.NOT_FOUND, "EMAIL_TOKEN_NOT_FOUND", "유효하지 않은 이메일 토큰입니다.");
    }
}
