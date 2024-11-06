package com.space.server.common.jwt.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotFoundException extends SpaceSecurityException {

    public RefreshTokenNotFoundException() {
        super(HttpStatus.UNAUTHORIZED, "REFRESH_TOKEN_NOT_FOUND", "리프레시 토큰이 존재하지 않습니다.");
    }
}
