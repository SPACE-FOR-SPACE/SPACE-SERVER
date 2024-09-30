package com.space.server.core.quiz.service;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.chapter.service.implementation.ChapterReader;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.presentation.dto.request.QuizRequest;
import com.space.server.core.quiz.service.implementation.QuizCreator;
import com.space.server.core.quiz.service.implementation.QuizDeleter;
import com.space.server.core.quiz.service.implementation.QuizReader;
import com.space.server.core.quiz.service.implementation.QuizUpdater;
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

  public void create(QuizRequest request) {
    Chapter chapter = chapterReader.findById(request.chapterId());
    Quiz quiz = request.toEntity(chapter);
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
