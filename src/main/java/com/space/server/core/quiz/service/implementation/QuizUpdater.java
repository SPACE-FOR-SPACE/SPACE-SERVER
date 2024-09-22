package com.space.server.core.quiz.service.implementation;

import com.space.server.core.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizUpdater {

  public void update(Quiz updatableQuiz, Quiz quiz) {
    updatableQuiz.update(quiz);
  }
}
