package com.space.server.ai.presentation.dto.request;

public record AiCodeRequest (
    Long userId,
    Long quizId,
    Long chapterId,
    String code
){}
