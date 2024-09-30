package com.space.server.ai.service.dto.response;

public record AiUsage (
    double queue,
    Integer prompt_token,
    double prompt_time,
    Integer completion_tokens,
    double completion_time,
    Integer total_tokens,
    double total_time
){
}
