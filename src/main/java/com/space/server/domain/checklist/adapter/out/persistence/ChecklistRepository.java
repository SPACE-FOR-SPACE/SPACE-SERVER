package com.space.server.domain.checklist.adapter.out.persistence;

import com.space.server.domain.quiz.adapter.out.persistence.QuizJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistRepository extends JpaRepository<ChecklistJpaEntity, Long> {
  List<ChecklistJpaEntity> findByQuiz(QuizJpaEntity quiz);
}
