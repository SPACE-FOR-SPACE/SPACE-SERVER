package com.space.server.core.quiz.presentation.dto.request;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;

public record QuizRequest(
    Chapter chapter,
    Long stepId,
    Long npcId,
    String title,
    String content,
    CharacterDirection characterDirection
) {
  public Quiz toEntity() {
    return new Quiz(chapter, stepId, npcId, title, content, characterDirection);
  }
}
