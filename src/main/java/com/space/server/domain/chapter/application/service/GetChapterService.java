package com.space.server.domain.chapter.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.chapter.application.port.in.GetChapterQuery;
import com.space.server.domain.chapter.application.port.out.LoadChapterPort;
import com.space.server.domain.chapter.domain.Chapter;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetChapterService implements GetChapterQuery {

  private final LoadChapterPort loadChapterPort;

  @Override
  public Chapter getChapter(Long chapterId) {
    return loadChapterPort.loadChapter(chapterId);
  }
}
