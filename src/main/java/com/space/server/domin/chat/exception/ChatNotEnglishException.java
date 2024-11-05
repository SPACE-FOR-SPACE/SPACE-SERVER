package com.space.server.domin.chat.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class ChatNotEnglishException extends SpaceException {

  public ChatNotEnglishException() {
    super(HttpStatus.BAD_REQUEST, "ENGLISH_DETECTED", "영어는 입력할 수 없습니다.");
  }
}
