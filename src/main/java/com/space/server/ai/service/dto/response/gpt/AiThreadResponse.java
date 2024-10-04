package com.space.server.ai.service.dto.response.gpt;

public record AiThreadResponse(
    String id,
    String object,
    Long created_at,
    String metadata,
    ToolResources tool_resources
) {
}
