package com.space.server.ai.service.dto.response.gpt;

import java.util.Map;

public record AiThreadResponse(
    String id,
    String object,
    Long created_at,
    Map<String, String> metadata,
    ToolResources tool_resources
) {}
