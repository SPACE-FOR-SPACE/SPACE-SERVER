package com.space.server.domain.state.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class StateNotSuccessException extends SpaceException {

  public StateNotSuccessException() {
    super(HttpStatus.UNPROCESSABLE_ENTITY, "QUIZ_NOT_SUCCESS", "퀴즈를 해결하지 못하였습니다.");
  }
}
