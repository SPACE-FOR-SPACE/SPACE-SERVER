package com.space.server.domain.chat.presentation.dto.request;

public record ReadQuizAndUserRequest(
    Long quizId,
    Long userId
) {}
