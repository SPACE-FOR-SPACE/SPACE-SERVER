package com.space.server.core.checklist.presentation.dto.response;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.quiz.domain.Quiz;

public record ChecklistResponse(
    Long checklistId,
    Quiz quiz,
    Chapter chapter,
    String content
) {
  public static ChecklistResponse from(Checklist checklist) {
    return new ChecklistResponse(
        checklist.getId(),
        checklist.getQuiz(),
        checklist.getChapter(),
        checklist.getContent()
    );
  }
}
