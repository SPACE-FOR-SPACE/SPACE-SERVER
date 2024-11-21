package com.space.server.domain.ai.service.dto.response.chatTune;

import java.util.List;
import java.util.Optional;

public record AiChatTuneResponse (
    String id,
    List<Choices> choices,
    Integer created,
    String model,
    Optional<Object> service_tier,
    String system_fingerprint,
    String object,
    Object usage
){
}
