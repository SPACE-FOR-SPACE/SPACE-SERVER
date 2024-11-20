package com.space.server.domain.checklist.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.checklist.application.port.out.LoadChecklistsByQuizPort;
import com.space.server.domain.checklist.domain.Checklist;
import com.space.server.domain.quiz.adapter.out.persistence.QuizJpaEntity;
import com.space.server.domain.quiz.adapter.out.persistence.QuizRepository;
import com.space.server.domain.quiz.exception.QuizNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChecklistsByQuizGetAdapter implements LoadChecklistsByQuizPort {

  private final ChecklistRepository checklistRepository;
  private final ChecklistMapper checklistMapper;
  private final QuizRepository quizRepository;

  @Override
  public List<Checklist> loadChecklistsByQuiz(Long quizId) {
    QuizJpaEntity quiz = quizRepository.findById(quizId)
        .orElseThrow(QuizNotFoundException::new);

    return checklistRepository.findByQuiz(quiz).stream()
        .map(checklistMapper::mapToChecklist)
        .toList();
  }
}
