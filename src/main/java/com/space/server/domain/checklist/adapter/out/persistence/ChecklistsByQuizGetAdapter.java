package com.space.server.domain.checklist.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.checklist.application.port.out.LoadChecklistsByQuizPort;
import com.space.server.domain.checklist.domain.Checklist;
import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.service.implementation.QuizReader;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChecklistsByQuizGetAdapter implements LoadChecklistsByQuizPort {

  private final ChecklistRepository checklistRepository;
  private final ChecklistMapper checklistMapper;
  private final QuizReader quizReader;

  @Override
  public List<Checklist> loadChecklistsByQuiz(Long quizId) {
    Quiz quiz = quizReader.findById(quizId);
    return checklistRepository.findByQuiz(quiz).stream()
        .map(checklistMapper::mapToChecklist)
        .toList();
  }
}
