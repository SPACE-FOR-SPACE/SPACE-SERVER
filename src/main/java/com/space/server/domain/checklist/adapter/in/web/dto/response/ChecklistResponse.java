package com.space.server.domain.checklist.adapter.in.web.dto.response;

import com.space.server.domain.checklist.domain.Checklist;
import io.swagger.v3.oas.annotations.media.Schema;

public record ChecklistResponse(

    @Schema(description = "체크리스트 ID", example = "1")
    Long checklistId,

    @Schema(description = "퀴즈 정보", required = true)
    Long quizId,

    @Schema(description = "챕터 정보", required = true)
    Long chapterId,

    @Schema(description = "체크리스트 내용", example = "바다거북이 구하기")
    String content
) {
  public static ChecklistResponse from(Checklist checklist) {
    return new ChecklistResponse(
        checklist.getId().getValue(),
        checklist.getQuiz().getId().getValue(),
        checklist.getChapter().getId().getValue(),
        checklist.getContent()
    );
  }
}
