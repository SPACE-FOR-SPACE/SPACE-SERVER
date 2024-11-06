package com.space.server.common.jwt.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import org.springframework.http.HttpStatus;

public class CustomAccessDeniedException extends SpaceSecurityException {
    public CustomAccessDeniedException() {
        super(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "권한이 필요합니다.");
    }
}
