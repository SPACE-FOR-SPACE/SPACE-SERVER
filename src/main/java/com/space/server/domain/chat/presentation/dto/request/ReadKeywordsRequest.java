package com.space.server.domain.chat.presentation.dto.request;

public record ReadKeywordsRequest(
    Long quizId,
    Long userId
) {}
