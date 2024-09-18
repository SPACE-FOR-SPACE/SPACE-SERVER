package com.space.server.core.chapter.presentation.dto.response;

import com.space.server.core.chapter.domain.Chapter;

import java.util.Map;

public record ChapterResponse(
    Long id,
    String explanation,
    Map<String, String> mapObject
) {
  public static ChapterResponse from(Chapter chapter) {
    return new ChapterResponse(chapter.getId(), chapter.getExplanation(), chapter.getMapObject());
  }
}
