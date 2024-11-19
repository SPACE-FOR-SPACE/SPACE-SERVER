package com.space.server.domain.chapter.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.chapter.application.port.out.LoadChapterPort;
import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.chapter.exception.ChapterNotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChapterGetAdapter implements LoadChapterPort {

  private final ChapterRepository chapterRepository;
  private final ChapterMapper chapterMapper;

  @Override
  public Chapter loadChapter(Long chapterId) {
    return chapterRepository.findById(chapterId)
        .map(chapterMapper::mapToChapter)
        .orElseThrow(ChapterNotFoundException::new);
  }
}
