package com.space.server.domain.checklist.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.checklist.application.port.in.GetChecklistsByQuizQuery;
import com.space.server.domain.checklist.application.port.out.LoadChecklistsByQuizPort;
import com.space.server.domain.checklist.domain.Checklist;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetChecklistsByQuizService implements GetChecklistsByQuizQuery {

  private final LoadChecklistsByQuizPort loadChecklistsByQuizPort;

  @Override
  public List<Checklist> getChecklistsByQuiz(Long quizId) {
    return loadChecklistsByQuizPort.loadChecklistsByQuiz(quizId);
  }
}
