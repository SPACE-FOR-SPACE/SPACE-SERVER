package com.space.server.domain.quiz.adapter.out.persistence;

import com.space.server.domain.chapter.adapter.out.persistence.ChapterJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizJpaEntity, Long> {
  List<QuizJpaEntity> findAllByChapterOrderByStepId(ChapterJpaEntity chapter);
}
