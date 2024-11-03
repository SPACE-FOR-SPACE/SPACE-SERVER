package com.space.server.common.jwt.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import org.springframework.http.HttpStatus;

public class UnauthenticatedAccessException extends SpaceSecurityException {
    public UnauthenticatedAccessException() {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHENTICATED_ACCESS", "인증이 필요합니다.");
    }
}