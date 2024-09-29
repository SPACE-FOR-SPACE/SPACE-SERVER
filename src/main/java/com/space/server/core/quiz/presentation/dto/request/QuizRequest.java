package com.space.server.core.quiz.presentation.dto.request;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record QuizRequest(

    @Schema(description = "챕터 ID", required = true)
    Long chapterId,

    @Schema(description = "스텝 ID", required = true)
    Long stepId,

    @Schema(description = "NPC ID", required = true)
    Long npcId,

    @Schema(description = "퀴즈 제목", required = true)
    String title,

    @Schema(description = "퀴즈 내용", required = true)
    String content,

    @Schema(description = "맵 정보", required = true)
    Integer[][] map,

    @Schema(description = "캐릭터 방향", required = true)
    CharacterDirection characterDirection,

    @Schema(description = "맵오브젝트", required = true)
    Map<String, String> mapObject,

    @Schema(description = "맵오브젝트 이미지", required = true)
    Map<String, String> mapObjectImage

) {
  public Quiz toEntity(Chapter chapter) {
    return Quiz.builder()
        .chapter(chapter)
        .stepId(stepId)
        .npcId(npcId)
        .title(title)
        .content(content)
        .map(map)
        .characterDirection(characterDirection)
        .mapObject(mapObject)
        .mapObjectImage(mapObjectImage)
        .build();
  }
}
