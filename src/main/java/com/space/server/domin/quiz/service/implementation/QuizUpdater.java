package com.space.server.domin.quiz.service.implementation;

import com.space.server.domin.quiz.domain.Quiz;
import org.springframework.stereotype.Service;

@Service
public class QuizUpdater {

  public void update(Quiz updatableQuiz, Quiz quiz) {
    updatableQuiz.update(quiz);
  }
}
