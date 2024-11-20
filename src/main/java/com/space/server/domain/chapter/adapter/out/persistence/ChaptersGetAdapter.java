package com.space.server.domain.chapter.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.chapter.application.port.out.LoadChaptersPort;
import com.space.server.domain.chapter.domain.Chapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChaptersGetAdapter implements LoadChaptersPort {

  private final ChapterRepository chapterRepository;
  private final ChapterMapper chapterMapper;

  @Override
  public List<Chapter> loadChapters() {
    return chapterRepository.findAll().stream()
        .map(chapterMapper::mapToChapter)
        .toList();
  }
}
