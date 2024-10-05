package com.space.server.ai.service.dto.response.gpt;

import com.space.server.ai.service.dto.response.AiResponse;

import java.util.List;

public record MessagesText(
    AiResponse value,
    List<String> annotations
) {
}
