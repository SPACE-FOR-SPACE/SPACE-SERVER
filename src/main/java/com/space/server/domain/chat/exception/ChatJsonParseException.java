package com.space.server.domain.chat.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class ChatJsonParseException extends SpaceException {
  public ChatJsonParseException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, "INVALID_CHAT_JSON", "JSON이 올바르지 않습니다.");
  }
}
