package com.space.server.domain.quiz.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.quiz.application.port.in.GetQuizQuery;
import com.space.server.domain.quiz.application.port.out.LoadQuizPort;
import com.space.server.domain.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetQuizService implements GetQuizQuery {

  private final LoadQuizPort loadQuizPort;

  @Override
  public Quiz getQuiz(Long quizId) {
    return loadQuizPort.loadQuiz(quizId);
  }
}
