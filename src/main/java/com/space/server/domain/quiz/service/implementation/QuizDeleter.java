package com.space.server.domain.quiz.service.implementation;

import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.domain.repository.QuizRepository;
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
