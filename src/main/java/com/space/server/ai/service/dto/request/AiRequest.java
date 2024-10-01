package com.space.server.ai.service.dto.request;

import java.util.List;

public record AiRequest (
        String model,
        List<AiChat> messages,
        Float temperature,
        Long max_tokens,
        Long top_p,
        boolean stream,
        ResponseFormat response_format,
        String stop
){
    public AiRequest toEntity() {
        return new AiRequest(model, messages, temperature, max_tokens, top_p, stream, response_format, stop);
    }
}
