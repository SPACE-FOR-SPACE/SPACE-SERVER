package com.space.server.core.quiz.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class QuizNotFoundException extends SpaceException {

  public QuizNotFoundException() {
    super(HttpStatus.NOT_FOUND, "QUIZ_NOT_FOUND", "문제를 찾을 수 없습니다.");
  }
}
