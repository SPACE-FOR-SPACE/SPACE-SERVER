package com.space.server.domin.quiz.service.implementation;

import com.space.server.domin.quiz.domain.Quiz;
import com.space.server.domin.quiz.domain.repository.QuizRepository;
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
