package com.space.server.domain.chapter.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterJpaEntity, Long> {
  List<ChapterJpaEntity> findAllByOrderById(Long chapterId);
}
