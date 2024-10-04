package com.space.server.ai.service.dto.response;

import java.util.List;

public record AiChoices(
    Integer index,
    List<ResponseAiChat> message,
    String logprobs,
    String finish_reason
) {
}
