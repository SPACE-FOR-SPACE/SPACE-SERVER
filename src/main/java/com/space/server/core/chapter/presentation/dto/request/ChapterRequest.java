package com.space.server.core.chapter.presentation.dto.request;

import com.space.server.core.chapter.domain.Chapter;

import java.util.Map;

public record ChapterRequest(
    String explanation,
    Map<String, String> mapObject,
    Map<String, String> mapObjectImage

) {
  public Chapter toEntity() {
    return new Chapter(explanation, mapObject, mapObjectImage);
  }
}
