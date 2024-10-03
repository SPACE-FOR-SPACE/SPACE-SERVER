package com.space.server.core.quiz.presentation.dto.request;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record QuizRequest(

    @Schema(description = "챕터 ID", example = "1", required = true)
    Long chapterId,

    @Schema(description = "스텝 ID", example = "1", required = true)
    Long stepId,

    @Schema(description = "퀴즈 제목", example = "바다거북이가 위험해", required = true)
    String title,

    @Schema(description = "퀴즈 내용", example = "바다거북이가 위험에 빠졌어요.", required = true)
    String content,

    @Schema(description = "맵 정보", example = "7x7 2차원 배열", required = true)
    Integer[][] map,

    @Schema(description = "캐릭터 방향", example = "RIGHT", required = true)
    CharacterDirection characterDirection,

    @Schema(description = "맵오브젝트", example = "'1001':'바다거북이'", required = true)
    Map<String, String> mapObject,

    @Schema(description = "맵오브젝트 이미지", example = "'1001':'바다거북이이미지URL'", required = true)
    Map<String, String> mapObjectImage

) {
  public Quiz toEntity(Chapter chapter) {
    return Quiz.builder()
        .chapter(chapter)
        .stepId(stepId)
        .title(title)
        .content(content)
        .map(map)
        .characterDirection(characterDirection)
        .mapObject(mapObject)
        .mapObjectImage(mapObjectImage)
        .build();
  }
}
