package com.space.server.ai.presentation.dto.request;

// Controller에서 사용하는 것
public record AiCodeRequest (
    Long userId,
    Long quizId,
    Long chapterId,
    String type,
    String code
){}
