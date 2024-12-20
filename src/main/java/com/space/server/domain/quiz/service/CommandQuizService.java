package com.space.server.domain.quiz.service;

import com.space.server.domain.chapter.service.implementation.ChapterReader;
import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.service.implementation.QuizCreator;
import com.space.server.domain.quiz.service.implementation.QuizDeleter;
import com.space.server.domain.quiz.service.implementation.QuizReader;
import com.space.server.domain.quiz.service.implementation.QuizUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandQuizService {

  private final QuizCreator quizCreator;
  private final QuizReader quizReader;
  private final QuizUpdater quizUpdater;
  private final QuizDeleter quizDeleter;
  private final ChapterReader chapterReader;

  public void create(Quiz quiz) {
    quizCreator.create(quiz);
  }

  public void update(Long quizId, Quiz quiz) {
    Quiz updatableQuiz = quizReader.findById(quizId);
    quizUpdater.update(updatableQuiz, quiz);
  }

  public void delete(Long quizId) {
    Quiz quiz = quizReader.findById(quizId);
    quizDeleter.delete(quiz);
  }
}
