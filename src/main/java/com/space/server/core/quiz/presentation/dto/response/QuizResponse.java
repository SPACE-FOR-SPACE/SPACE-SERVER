package com.space.server.core.quiz.presentation.dto.response;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;
import io.swagger.v3.oas.annotations.media.Schema;

public record QuizResponse(

    @Schema(description = "챕터 정보", required = true)
    Chapter chapter,

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
    CharacterDirection characterDirection
) {
  public static QuizResponse from(Quiz quiz) {
    return new QuizResponse(
        quiz.getChapter(),
        quiz.getStepId(),
        quiz.getNpcId(),
        quiz.getTitle(),
        quiz.getContent(),
        quiz.getMap(),
        quiz.getCharacterDirection()
    );
  }
}
