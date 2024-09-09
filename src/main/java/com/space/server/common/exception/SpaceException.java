package com.space.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SpaceException extends RuntimeException {
    private final ErrorCode errorCode;
}

