package com.space.server.core.chapter.domain.repository;

import com.space.server.core.chapter.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
  List<Chapter> findAllByOrderById();
}
