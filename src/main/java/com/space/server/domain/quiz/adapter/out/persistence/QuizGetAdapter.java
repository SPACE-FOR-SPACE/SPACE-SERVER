package com.space.server.domain.quiz.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.quiz.application.port.out.LoadQuizPort;
import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.exception.QuizNotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class QuizGetAdapter implements LoadQuizPort {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;

  @Override
  public Quiz loadQuiz(Long quizId) {
    return quizRepository.findById(quizId)
        .map(quizMapper::mapToQuiz)
        .orElseThrow(QuizNotFoundException::new);
  }
}
