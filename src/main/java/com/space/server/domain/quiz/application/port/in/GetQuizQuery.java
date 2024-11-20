package com.space.server.domain.quiz.application.port.in;

import com.space.server.domain.quiz.domain.Quiz;

public interface GetQuizQuery {

  Quiz getQuiz(Long quizId);
}
