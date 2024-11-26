package com.space.server.domain.oauth.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import org.springframework.http.HttpStatus;

public class SocialUserExistedException extends SpaceSecurityException {
    public SocialUserExistedException() {
        super(HttpStatus.CONFLICT, "USER_EXISTED", "유저가 이미 존재합니다.");
    }
}
