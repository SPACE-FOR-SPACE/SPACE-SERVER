package com.space.server.core.chapter.presentation.dto.response;

import com.space.server.core.chapter.domain.Chapter;

public record ChapterResponse(
    String explanation
) {
  public static ChapterResponse from(Chapter chapter) {
    return new ChapterResponse(chapter.getExplanation());
  }
}
