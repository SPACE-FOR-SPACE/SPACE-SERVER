package com.space.server.domain.ai.service.dto.request.gpt.chatTune;

import com.space.server.domain.ai.service.dto.request.gpt.AiChat;

import java.util.List;

public record AiChatTune(
    String model,
    List<AiChat> messages,
    Double top_p,
    Double temperature
) {
}
