package com.space.server.ai.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.server.ai.service.dto.request.AiRequest;
import com.space.server.ai.service.dto.response.AiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class ChatCompleter {

    private final String apiKey;
    private final RestTemplate restTemplate;

    public ChatCompleter(RestTemplate restTemplate, @Value("${groq.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public AiResponse completerChat(AiRequest request) {
        String url = "https://api.groq.com/openai/v1/chat/completions";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);

        // 요청 엔티티 생성
        HttpEntity<AiRequest> httpEntity = new HttpEntity<>(request, headers);

        try {
            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            // 응답 바디 가져오기
            String responseBody = responseEntity.getBody();

            // JSON을 Map으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            AiResponse responseMap = objectMapper.readValue(responseBody, AiResponse.class);

            // 응답 처리
            log.info("Response: " + responseMap);
            return responseMap;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return null;
        }
    }
}
