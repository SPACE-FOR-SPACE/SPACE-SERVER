package com.space.server.ai.presentation.dto.request;

import com.space.server.core.quiz.domain.Quiz;

import java.util.ArrayList;

public record AiRequest (
    String model,
    ArrayList<AiChat> messages,
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

