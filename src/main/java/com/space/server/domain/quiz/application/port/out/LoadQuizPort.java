package com.space.server.domain.quiz.application.port.out;

import com.space.server.domain.quiz.domain.Quiz;

public interface LoadQuizPort {

  Quiz loadQuiz(Long quizId);
}
