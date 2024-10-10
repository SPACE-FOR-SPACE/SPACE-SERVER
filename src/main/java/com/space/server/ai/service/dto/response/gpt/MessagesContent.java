package com.space.server.ai.service.dto.response.gpt;

public record MessagesContent(
    String type,
    MessagesText text
) {}
