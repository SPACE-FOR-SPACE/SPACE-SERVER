package com.space.server.domain.user.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends SpaceException {

  public UserNotFoundException() {
    super(HttpStatus.NOT_FOUND, "USER_NOT_FOUND","유저를 찾을 수 없습니다.");
  }
}
