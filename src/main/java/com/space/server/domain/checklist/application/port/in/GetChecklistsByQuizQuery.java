package com.space.server.domain.checklist.application.port.in;

import com.space.server.domain.checklist.domain.Checklist;

import java.util.List;

public interface GetChecklistsByQuizQuery {
  List<Checklist> getChecklistsByQuiz(Long quizId);
}
