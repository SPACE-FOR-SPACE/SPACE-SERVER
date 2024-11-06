package com.space.server.domain.checklist.domain.repository;

import com.space.server.domain.checklist.domain.Checklist;
import com.space.server.domain.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
  List<Checklist> findByQuiz(Quiz quiz);
}
