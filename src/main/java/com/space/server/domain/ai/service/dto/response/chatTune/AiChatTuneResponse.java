package com.space.server.domain.ai.service.dto.response.chatTune;

import java.util.List;
import java.util.Optional;

public record AiChatTuneResponse (
    String id,
    String object,
    Integer created,
    String model,
    List<Choices> choices,
    Object usage,
    String system_fingerprint,
    Object service_tier
){
}
