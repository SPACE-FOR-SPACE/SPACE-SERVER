package com.space.server.domain.chapter.service.implementation;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.chapter.domain.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterDeleter {

  private final ChapterRepository chapterRepository;

  public void delete(Chapter chapter) {
    chapterRepository.delete(chapter);
  }
}
