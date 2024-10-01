package com.space.server.ai.service.dto.response;

import com.space.server.ai.service.dto.request.AiChat;

import java.util.List;
import java.util.Map;

public record AiChoices(
    Integer index,
    List<ResponseAiChat> message,
    String logprobs,
    String finish_reason
) {
}
