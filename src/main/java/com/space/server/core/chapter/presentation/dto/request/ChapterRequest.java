package com.space.server.core.chapter.presentation.dto.request;

import com.space.server.core.chapter.domain.Chapter;

public record ChapterRequest(
    String explanation
) {
  public Chapter toEntity() {
    return new Chapter(explanation);
  }
}
