package com.space.server.ai.service;

import com.space.server.ai.presentation.dto.request.AiRequest;
import com.space.server.ai.presentation.dto.response.AiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GroqApiClientImpl {
    private final GroqApiClient groqApiClient;
    private final String apiKey;

    public GroqApiClientImpl(GroqApiClient groqApiClient, @Value("${groq.api.key}") String apiKey) {
        this.groqApiClient = groqApiClient;
        this.apiKey = apiKey;
    }

    public Map createChatCompletion(AiRequest request) {
        return groqApiClient.createChatCompletion(request, "Bearer " + apiKey);
    }
}
