package com.space.server.core.quiz.presentation.dto.request;

import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateQuizRequest(

    @Schema(description = "맵 정보", required = true)
    Integer[][] map,

    @Schema(description = "캐릭터 방향", required = true)
    CharacterDirection characterDirection
) {
  public Quiz toEntity() {
    return Quiz.builder()
        .map(map)
        .characterDirection(characterDirection)
        .build();
  }
}
