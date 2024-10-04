package com.space.server.ai.service.dto.response.gpt;

import java.util.List;

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
    String metadata
) {
}
