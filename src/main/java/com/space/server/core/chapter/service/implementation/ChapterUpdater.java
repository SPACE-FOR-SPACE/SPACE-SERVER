package com.space.server.core.chapter.service.implementation;

import com.space.server.core.chapter.domain.Chapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterUpdater {

  public void update(Chapter updatableChapter, Chapter chapter) {
    updatableChapter.update(chapter);
  }
}
