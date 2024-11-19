package com.space.server.domain.chapter.application.port.out;

import com.space.server.domain.chapter.domain.Chapter;

public interface LoadChapterPort {

  Chapter loadChapter(Long chapterId);
}
