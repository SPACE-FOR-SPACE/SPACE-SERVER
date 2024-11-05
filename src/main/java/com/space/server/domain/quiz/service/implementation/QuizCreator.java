package com.space.server.domain.quiz.service.implementation;

import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.domain.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizCreator {

  private final QuizRepository quizRepository;

  public void create(Quiz quiz) {
    quizRepository.save(quiz);
  }
}
