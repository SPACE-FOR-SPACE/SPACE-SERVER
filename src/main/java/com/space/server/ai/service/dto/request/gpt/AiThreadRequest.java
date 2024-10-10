package com.space.server.ai.service.dto.request.gpt;

import java.util.List;

public record AiThreadRequest(
    List<AiChat> messages
) {}
