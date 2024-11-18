package com.space.server.domain.checklist.application.port.out;

import com.space.server.domain.checklist.domain.Checklist;

import java.util.List;

public interface LoadChecklistsByQuizPort {
  List<Checklist> loadChecklistsByQuiz(Long quizId);
}
