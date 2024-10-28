package com.space.server.common.exception.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class SpaceSecurityException extends AuthenticationException {
    private final HttpStatus status;
    private final String errorCode;

    public SpaceSecurityException(HttpStatus status, String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
