package com.space.server.common.exception.mail;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class SpaceMailException extends SpaceException {
    public SpaceMailException(HttpStatus status, String errorCode, String message) {
        super(status, errorCode, message);
    }
}
