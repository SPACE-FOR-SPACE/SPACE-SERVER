package com.space.server.domain.ai.service.dto.response.gpt;

public record MessagesContent(
    String type,
    MessagesText text
) {}
