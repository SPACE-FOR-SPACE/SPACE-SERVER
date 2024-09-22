package com.space.server.core.checklist.domain.repository;

import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
  public List<Checklist> findByQuiz(Quiz quiz);
}
