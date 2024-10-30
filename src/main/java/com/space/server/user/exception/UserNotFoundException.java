package com.space.server.user.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends SpaceException {

  public UserNotFoundException() {
    super(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.");
  }
}
