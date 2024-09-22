package com.space.server.core.checklist.presentation.dto.request;

public record ChecklistRequest(
    Long quizId,
    Long chapterId,
    String content
) {}
