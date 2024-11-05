package com.space.server.domin.quiz.domain.repository;

import com.space.server.domin.chapter.domain.Chapter;
import com.space.server.domin.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  Quiz findByChapterAndStepId(Chapter chapter, Long stepId);
  List<Quiz> findAllByChapterOrderByStepId(Chapter chapter);
}
