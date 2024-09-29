package com.space.server.core.quiz.presentation.dto.request;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;

import java.util.Map;

public record QuizRequest(
    Long chapterId,
    Long stepId,
    Long npcId,
    String title,
    String content,
    Integer[][] map,
    CharacterDirection characterDirection,
    Map<String, String> mapObject,
    Map<String, String> mapObjectImage

) {
  public Quiz toEntity(Chapter chapter) {
    return new Quiz(chapter, stepId, npcId, title, content, map, characterDirection, mapObject, mapObjectImage);
  }
}
