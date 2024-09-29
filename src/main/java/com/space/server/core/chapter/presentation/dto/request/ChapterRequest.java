package com.space.server.core.chapter.presentation.dto.request;

import com.space.server.core.chapter.domain.Chapter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record ChapterRequest(
    @Schema(description = "챕터 설명", example = "바다와 관련된 챕터입니다.")
    String explanation,

    @Schema(description = "맵오브젝트", example = "{\"1\": \"chapter\", \"2\": \"target\"}")
    Map<String, String> mapObject,

    @Schema(description = "맵오브젝트 이미지", example = "{\"1\": \"chapterURL\", \"2\": \"targetURL\"}")
    Map<String, String> mapObjectImage
) {
  public Chapter toEntity() {
    return Chapter.builder()
        .explanation(explanation)
        .mapObject(mapObject)
        .mapObjectImage(mapObjectImage)
        .build();
  }
}
