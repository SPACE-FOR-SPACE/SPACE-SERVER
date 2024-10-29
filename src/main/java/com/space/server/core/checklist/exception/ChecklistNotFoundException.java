package com.space.server.core.checklist.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class ChecklistNotFoundException extends SpaceException {

  public ChecklistNotFoundException() {
    super(HttpStatus.NOT_FOUND, "체크리스트를 찾을 수 없습니다.");
  }
}