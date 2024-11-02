package com.space.server.chat.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class ChatJsonParseException extends SpaceException {
  public ChatJsonParseException() {
    super(HttpStatus.BAD_REQUEST, "JSON이 올바르지 않습니다.");
  }
}
