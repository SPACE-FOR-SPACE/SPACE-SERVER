package com.space.server.domain.auth.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import org.springframework.http.HttpStatus;

public class AuthUserNotFoundException extends SpaceSecurityException {

    public AuthUserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "USER_NOT_FOUND","유저를 찾을 수 없습니다.");
    }
}
