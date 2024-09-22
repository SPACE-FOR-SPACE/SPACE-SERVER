package com.space.server.core.quiz.service.implementation;

import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizDeleter {

  private final QuizRepository quizRepository;

  public void delete(Quiz quiz) {
    quizRepository.delete(quiz);
  }
}
