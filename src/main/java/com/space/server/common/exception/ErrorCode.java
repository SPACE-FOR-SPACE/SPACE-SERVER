package com.space.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // auth
    EMPTY_AUTHORIZATION_HEADER(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    SECRET_KEY_MISMATCH(HttpStatus.UNAUTHORIZED, "기존의 시크릿 키와 일치하지 않습니다."),
    AUTH_REQUIRED(HttpStatus.UNAUTHORIZED, "인증 정보가 필요합니다."),

    // user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),

    // chapter
    CHAPTER_NOT_FOUND(HttpStatus.NOT_FOUND, "챕터를 찾을 수 없습니다."),

    // quiz
    QUIZ_NOT_FOUND(HttpStatus.NOT_FOUND, "문제를 찾을 수 없습니다."),
    STEP_NOT_FOUND(HttpStatus.NOT_FOUND, "단게를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
