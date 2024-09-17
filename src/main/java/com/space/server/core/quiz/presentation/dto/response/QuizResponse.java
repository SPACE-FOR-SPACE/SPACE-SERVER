package com.space.server.core.quiz.presentation.dto.response;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;

public record QuizResponse(
    Chapter chapter,
    Long stepId,
    Long npcId,
    String title,
    String content,
    CharacterDirection characterDirection
) {
  public static QuizResponse from(Quiz quiz) {
    return new QuizResponse(
        quiz.getChapter(),
        quiz.getStepId(),
        quiz.getNpcId(),
        quiz.getTitle(),
        quiz.getContent(),
        quiz.getCharacterDirection()
    );
  }
}
