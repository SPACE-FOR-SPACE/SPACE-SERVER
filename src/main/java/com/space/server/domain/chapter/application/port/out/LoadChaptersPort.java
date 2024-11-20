package com.space.server.domain.chapter.application.port.out;

import com.space.server.domain.chapter.domain.Chapter;

import java.util.List;

public interface LoadChaptersPort {

  List<Chapter> loadChapters();
}
