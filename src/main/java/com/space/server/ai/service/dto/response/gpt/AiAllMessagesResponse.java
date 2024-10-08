package com.space.server.ai.service.dto.response.gpt;

import java.util.List;

public record AiAllMessagesResponse(
    String object,
    List<AiMessagesResponse> data,
    String first_id,
    String last_id,
    Boolean has_more
) {
}
