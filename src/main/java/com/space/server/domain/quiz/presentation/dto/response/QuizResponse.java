package com.space.server.domain.quiz.presentation.dto.response;

import com.space.server.domain.chapter.presentation.dto.response.ChapterResponse;
import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.domain.value.CharacterDirection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record QuizResponse(

    @Schema(description = "ID", example = "1", required = true)
    Long id,

    @Schema(description = "챕터 정보", example = "1", required = true)
    ChapterResponse chapter,


    @Schema(description = "퀴즈 제목", example = "바다거북이가 위험해", required = true)
    String title,

    @Schema(description = "퀴즈 내용", example = "바다거북이가 위험에 빠졌어요.", required = true)
    String content,

    @Schema(description = "맵 정보", example = "7x7 2차원 배열", required = true)
    Integer[][] map,

    @Schema(description = "캐릭터 방향", example = "RIGHT", required = true)
    CharacterDirection characterDirection,

    @Schema(description = "맵오브젝트", example = "{\"1\": \"chapter\", \"2\": \"target\"}")
    Map<String, String> mapObject,

    @Schema(description = "맵오브젝트이미지", example = "{\"1\": \"character_URL\", \"2\": \"target_URL\"}")
    Map<String, String> mapObjectImage
) {
  public static QuizResponse from(Quiz quiz) {
    return new QuizResponse(
        quiz.getId(),
        ChapterResponse.from(quiz.getChapter()),
        quiz.getTitle(),
        quiz.getContent(),
        quiz.getMap(),
        quiz.getCharacterDirection(),
        quiz.getMapObject(),
        quiz.getMapObjectImage()
    );
  }
}
