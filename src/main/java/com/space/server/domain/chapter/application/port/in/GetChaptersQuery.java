package com.space.server.domain.chapter.application.port.in;

import com.space.server.domain.chapter.domain.Chapter;

import java.util.List;

public interface GetChaptersQuery {

  List<Chapter> getChapters();
}
