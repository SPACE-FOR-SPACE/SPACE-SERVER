package com.space.server.domain.user.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class InsufficientPointsException extends SpaceException {

  public InsufficientPointsException() {
    super(HttpStatus.CONFLICT, "INSUFFICIENT_POINTS", "포인트가 부족합니다.");
  }
}
