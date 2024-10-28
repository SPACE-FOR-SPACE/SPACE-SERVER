package com.space.server.common.jwt.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import org.springframework.http.HttpStatus;

public class DuplicateLoginException extends SpaceSecurityException {

    public DuplicateLoginException() {
        super(HttpStatus.UNAUTHORIZED, "DUPLICATE_LOGIN", "이미 로그인한 상태입니다.");
    }
}
