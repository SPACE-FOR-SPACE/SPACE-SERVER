package com.space.server.domain.chat.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class ChatNotFoundException extends SpaceException {

  public ChatNotFoundException() {
    super(HttpStatus.NOT_FOUND, "CHAT_NOT_FOUND", "채팅을 찾을 수 없습니다.");
  }
}
