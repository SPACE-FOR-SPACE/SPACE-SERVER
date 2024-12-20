package com.space.server.domain.chapter.service;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.chapter.service.implementation.ChapterReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryChapterService {

  private final ChapterReader chapterReader;

  public Chapter readOne(Long chapterId) {
    return chapterReader.findById(chapterId);
  }

  public List<Chapter> readAll() {
    return chapterReader.findAll();
  }
}
