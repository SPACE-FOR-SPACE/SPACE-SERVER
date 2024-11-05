package com.space.server.domain.chapter.service;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.chapter.service.implementation.ChapterCreator;
import com.space.server.domain.chapter.service.implementation.ChapterDeleter;
import com.space.server.domain.chapter.service.implementation.ChapterReader;
import com.space.server.domain.chapter.service.implementation.ChapterUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandChapterService {

  private final ChapterCreator chapterCreator;
  private final ChapterReader chapterReader;
  private final ChapterUpdater chapterUpdater;
  private final ChapterDeleter chapterDeleter;

  public void createChapter(Chapter chapter) {
    chapterCreator.create(chapter);
  }

  public void updateChapter(Long chapterId, Chapter chapter) {
    Chapter updatableChapter = chapterReader.findById(chapterId);
    chapterUpdater.update(updatableChapter, chapter);
  }

  public void deleteChapter(Long chapterId) {
    Chapter chapter = chapterReader.findById(chapterId);
    chapterDeleter.delete(chapter);
  }
}
