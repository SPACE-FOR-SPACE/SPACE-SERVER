package com.space.server.ai.service.dto.response.gpt;

import java.util.List;

public record MessagesText(
    String value,
    List<String> annotations
) {
}
