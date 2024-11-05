package com.space.server.domain.chapter.service.implementation;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.chapter.domain.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterCreator {

  private final ChapterRepository chapterRepository;

  public void create(Chapter chapter) {
    chapterRepository.save(chapter);
  }
}
