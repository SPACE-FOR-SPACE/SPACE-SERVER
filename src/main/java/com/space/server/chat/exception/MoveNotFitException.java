package com.space.server.chat.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class MoveNotFitException extends SpaceException {

  public MoveNotFitException() {
    super(HttpStatus.BAD_REQUEST, "move가 올바르지 않습니다.");
  }
}
