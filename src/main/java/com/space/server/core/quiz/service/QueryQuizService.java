package com.space.server.core.quiz.service;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.chapter.service.implementation.ChapterReader;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.service.implementation.QuizReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryQuizService {

  private final QuizReader quizReader;
  private final ChapterReader chapterReader;

  public Quiz readOne(Long quizId) {
    return quizReader.findById(quizId);
  }

  public List<Quiz> readAll() {
    return quizReader.findAll();
  }

  public Quiz findOne(Long chapterId, Long stepId) {
    Chapter chapter = chapterReader.findById(chapterId);
    return quizReader.findByChapterAndStepId(chapter, stepId);
  }

  public List<Quiz> findAll(Long chapterId) {
    Chapter chapter = chapterReader.findById(chapterId);
    return quizReader.findByChapter(chapter);
  }
}
