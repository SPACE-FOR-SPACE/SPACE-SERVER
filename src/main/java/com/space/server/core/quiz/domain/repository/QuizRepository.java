package com.space.server.core.quiz.domain.repository;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
  public Quiz findByChapterAndStepId(Chapter chapter, Long stepId);
  public List<Quiz> findByChapter(Chapter chapter);
}
