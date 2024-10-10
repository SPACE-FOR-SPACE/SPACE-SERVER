package com.space.server.ai.service.dto.response.gpt.tools;

public record FunctionTool(
    String type,
    Function function
) {
}
