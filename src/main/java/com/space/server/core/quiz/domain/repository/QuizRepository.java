package com.space.server.core.quiz.domain.repository;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  Quiz findByChapterAndStepId(Chapter chapter, Long stepId);
  List<Quiz> findAllByOrderById(Chapter chapter);
}
