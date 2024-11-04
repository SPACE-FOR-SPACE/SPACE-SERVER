package com.space.server.state.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class StateNotFoundException extends SpaceException {

  public StateNotFoundException() {
    super(HttpStatus.NOT_FOUND, "STATE_NOT_FOUND","상태를 찾을 수 없습니다.");
  }
}
