package com.space.server.ai.service.dto.response.gpt.tools;

import java.util.Map;

public record Function(
    String description,
    String name,
    Map<String, String> parameters,
    Boolean strict
) {
}
