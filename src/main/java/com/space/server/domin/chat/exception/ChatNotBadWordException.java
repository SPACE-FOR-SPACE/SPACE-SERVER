package com.space.server.domin.chat.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class ChatNotBadWordException extends SpaceException {

  public ChatNotBadWordException() {
    super(HttpStatus.BAD_REQUEST, "BAD_WORD_DETECTED", "부적절한 단어는 입력할 수 없습니다.");
  }
}
