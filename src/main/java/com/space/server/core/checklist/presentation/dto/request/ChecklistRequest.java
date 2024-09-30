package com.space.server.core.checklist.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ChecklistRequest(

    @Schema(description = "퀴즈 ID", example = "1", required = true)
    Long quizId,

    @Schema(description = "챕터 ID", example = "1", required = true)
    Long chapterId,

    @Schema(description = "체크리스트 내용", example = "바다 거북이에게 다가가기", required = true)
    String content
) {}
