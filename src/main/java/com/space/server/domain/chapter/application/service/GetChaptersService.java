package com.space.server.domain.chapter.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.chapter.application.port.in.GetChaptersQuery;
import com.space.server.domain.chapter.application.port.out.LoadChaptersPort;
import com.space.server.domain.chapter.domain.Chapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetChaptersService implements GetChaptersQuery {

  private final LoadChaptersPort loadChaptersPort;

  @Override
  public List<Chapter> getChapters() {
    return loadChaptersPort.loadChapters();
  }
}
