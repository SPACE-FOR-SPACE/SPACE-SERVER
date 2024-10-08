package com.space.server.ai.service.dto.response.gpt;

import java.util.List;
import java.util.Map;

public record AiMessagesResponse(
    String id,
    String object,
    Long created_at,
    String assistant_id,
    String thread_id,
    String run_id,
    String role,
    List<MessagesContent> content,
    List<String> attachments,
    Map<String, String> metadata
) {
}
