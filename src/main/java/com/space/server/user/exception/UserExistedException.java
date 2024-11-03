package com.space.server.user.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class UserExistedException extends SpaceException {

  public UserExistedException() {
    super(HttpStatus.CONFLICT, "USER_EXISTED", "유저가 이미 존재합니다.");
  }
}
