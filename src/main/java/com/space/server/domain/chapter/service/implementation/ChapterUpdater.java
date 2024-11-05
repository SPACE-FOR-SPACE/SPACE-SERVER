package com.space.server.domain.chapter.service.implementation;

import com.space.server.domain.chapter.domain.Chapter;
import org.springframework.stereotype.Service;

@Service
public class ChapterUpdater {

  public void update(Chapter updatableChapter, Chapter chapter) {
    updatableChapter.update(chapter);
  }
}
