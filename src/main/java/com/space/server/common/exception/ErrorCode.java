package com.space.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    INSUFFICIENT_POINTS(HttpStatus.FORBIDDEN, "포인트가 부족합니다."),
    USER_EXISTED(HttpStatus.CONFLICT, "유저가 이미 존재합니다."),

    // chat
    CHAT_NOT_ENGLISH(HttpStatus.BAD_REQUEST, "영어는 입력할 수 없습니다."),
    CHAT_NOT_BAD_WORD(HttpStatus.BAD_REQUEST, "부적절한 단어는 입력할 수 없습니다."),

    // item
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "아이템을 찾을 수 없습니다."),

    // quiz
    QUIZ_NOT_FOUND(HttpStatus.NOT_FOUND, "문제를 찾을 수 없습니다."),

    //state
    STATE_NOT_FOUND(HttpStatus.NOT_FOUND, "상태를 찾을 수 없습니다."),

    //ai
    MOVE_NOT_FIT(HttpStatus.BAD_REQUEST, "move가 올바르지 않습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
