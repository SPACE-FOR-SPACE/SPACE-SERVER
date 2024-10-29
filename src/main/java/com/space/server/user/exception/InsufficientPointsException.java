package com.space.server.user.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class InsufficientPointsException extends SpaceException {

  public InsufficientPointsException() {
    super(HttpStatus.FORBIDDEN, "포인트가 부족합니다.");
  }
}