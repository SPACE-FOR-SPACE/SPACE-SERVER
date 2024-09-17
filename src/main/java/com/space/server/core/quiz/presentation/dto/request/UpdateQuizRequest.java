package com.space.server.core.quiz.presentation.dto.request;

import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;

public record UpdateQuizRequest(
    CharacterDirection characterDirection
) {
  public Quiz toEntity() {
    return new Quiz(characterDirection);
  }
}
