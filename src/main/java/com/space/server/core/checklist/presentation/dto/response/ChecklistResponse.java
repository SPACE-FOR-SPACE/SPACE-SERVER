package com.space.server.core.checklist.presentation.dto.response;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.quiz.domain.Quiz;
import io.swagger.v3.oas.annotations.media.Schema;

public record ChecklistResponse(

    @Schema(description = "체크리스트 ID", example = "1")
    Long checklistId,

    @Schema(description = "퀴즈 정보", required = true)
    Quiz quiz,

    @Schema(description = "챕터 정보", required = true)
    Chapter chapter,

    @Schema(description = "체크리스트 내용", example = "바다거북이 구하기")
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
