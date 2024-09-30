package com.space.server.ai.service;

import com.space.server.ai.presentation.dto.request.AiRequest;
import com.space.server.ai.presentation.dto.response.AiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "groqApiClient", url = "https://api.groq.com")
public interface GroqApiClient {

    @PostMapping(value = "/openai/v1/chat/completions",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "Authorization=Bearer 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")
    Map createChatCompletion(@RequestBody AiRequest request, @RequestHeader("Authorization") String apiKey);
}
