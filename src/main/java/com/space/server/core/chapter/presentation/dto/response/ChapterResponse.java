package com.space.server.core.chapter.presentation.dto.response;

import com.space.server.core.chapter.domain.Chapter;

public record ChapterResponse(
    Long id,
    String explanation
) {
  public static ChapterResponse from(Chapter chapter) {
    return new ChapterResponse(chapter.getId(), chapter.getExplanation());
  }
}
