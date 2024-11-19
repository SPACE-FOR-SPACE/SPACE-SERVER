package com.space.server.domain.chapter.application.port.in;

import com.space.server.domain.chapter.domain.Chapter;

public interface GetChapterQuery {

  Chapter getChapter(Long id);
}
