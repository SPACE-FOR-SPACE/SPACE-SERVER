package com.space.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class SpaceException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}

