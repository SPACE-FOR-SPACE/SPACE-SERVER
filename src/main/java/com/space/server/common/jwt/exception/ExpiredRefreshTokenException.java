package com.space.server.common.jwt.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import org.springframework.http.HttpStatus;

public class ExpiredRefreshTokenException extends SpaceSecurityException {

    public ExpiredRefreshTokenException() {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_REFRESH_TOKEN", "재로그인 해야 합니다.");
    }
}
