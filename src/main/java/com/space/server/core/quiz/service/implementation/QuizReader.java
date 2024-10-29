package com.space.server.core.quiz.service.implementation;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.repository.QuizRepository;
import com.space.server.core.quiz.exception.QuizNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizReader {

  private final QuizRepository quizRepository;

  public Quiz findById(Long quizId) {
    return quizRepository.findById(quizId)
        .orElseThrow(QuizNotFoundException::new);
  }

  public List<Quiz> findAll() {
    return quizRepository.findAll();
  }

  public List<Quiz> findAll(Chapter chapter) {
    return quizRepository.findAllByChapterOrderByStepId(chapter);
  }

  public Quiz findByChapterAndStepId(Chapter chapter, Long stepId) {
    return quizRepository.findByChapterAndStepId(chapter, stepId);
  }
}
