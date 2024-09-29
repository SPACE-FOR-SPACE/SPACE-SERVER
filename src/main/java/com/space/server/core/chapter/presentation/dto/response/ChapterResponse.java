package com.space.server.core.chapter.presentation.dto.response;

import com.space.server.core.chapter.domain.Chapter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record ChapterResponse(
    @Schema(description = "챕터 ID", example = "1")
    Long id,

    @Schema(description = "챕터 설명", example = "바다와 관련된 챕터입니다.")
    String explanation,

    @Schema(description = "맵오브젝트", example = "{\"1\": \"chapterURL\", \"2\": \"targetURL\"}")
    Map<String, String> mapObject
) {
  public static ChapterResponse from(Chapter chapter) {
    return new ChapterResponse(chapter.getId(), chapter.getExplanation(), chapter.getMapObject());
  }
}
