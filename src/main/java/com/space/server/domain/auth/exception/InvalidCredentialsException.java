package com.space.server.domain.auth.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends SpaceSecurityException {
    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "잘못된 이메일 또는 비밀번호입니다.");
    }
}