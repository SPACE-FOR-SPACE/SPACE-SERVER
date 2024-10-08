package com.space.server.ai.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.server.ai.service.dto.request.gpt.AiAssistantsRequest;
import com.space.server.ai.service.dto.request.gpt.AiChat;
import com.space.server.ai.service.dto.request.gpt.AiRunsRequest;
import com.space.server.ai.service.dto.request.gpt.AiThreadRequest;
import com.space.server.ai.service.dto.response.gpt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ChatCompleter {

    private final String apiKey;
    private final String assistantsKey;
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://api.openai.com/v1/threads";

    public ChatCompleter(RestTemplate restTemplate, @Value("${gpt.api.key}") String apiKey, @Value("${gpt.assistants.key}") String assistantsKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.assistantsKey = assistantsKey;
    }

    public AiThreadResponse threadCreate() {
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v2");

        // 요청 엔티티 생성
        HttpEntity<AiThreadRequest> httpEntity = new HttpEntity<>(headers);

        try {
            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            // 응답 바디 가져오기
            String responseBody = responseEntity.getBody();

            // JSON을 Map으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            AiThreadResponse responseMap = objectMapper.readValue(responseBody, AiThreadResponse.class);

            // 응답 처리
            log.info("Response: " + responseMap);
            return responseMap;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return null;
        }
    }

    public AiMessagesResponse messageCreate(String id, AiChat aiChat) {
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v2");

        // 요청 엔티티 생성
        HttpEntity<AiChat> httpEntity = new HttpEntity<>(aiChat, headers);

        try {
            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    baseUrl + "/" + id + "/messages",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            // 응답 바디 가져오기
            String responseBody = responseEntity.getBody();

            // JSON을 Map으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            AiMessagesResponse responseMap = objectMapper.readValue(responseBody, AiMessagesResponse.class);

            // 응답 처리
            log.info("Response: " + responseMap);
            return responseMap;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return null;
        }
    }

    public AiAllMessagesResponse messageAllSelect(String thread_id) {
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v2");

        // 요청 엔티티 생성
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        try {
            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    baseUrl + "/" + thread_id + "/messages",
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );

            // 응답 바디 가져오기
            String responseBody = responseEntity.getBody();

            // JSON을 Map으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            AiAllMessagesResponse responseMap = objectMapper.readValue(responseBody, AiAllMessagesResponse.class);

            // 응답 처리
            log.info("Response: " + responseMap);
            return responseMap;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return null;
        }
    }

    public AiMessagesResponse messageOneSelect(String thread_id, String message_id) {
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v2");

        // 요청 엔티티 생성
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        try {
            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    baseUrl + "/" + thread_id + "/messages/" + message_id,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );

            // 응답 바디 가져오기
            String responseBody = responseEntity.getBody();

            // JSON을 Map으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            AiMessagesResponse responseMap = objectMapper.readValue(responseBody, AiMessagesResponse.class);

            // 응답 처리
            log.info("Response: " + responseMap);
            return responseMap;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return null;
        }
    }

    public AiRunsResponse runsCreate(String id) {
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v2");

        AiRunsRequest request = new AiRunsRequest(assistantsKey);

        // 요청 엔티티 생성
        HttpEntity<AiRunsRequest> httpEntity = new HttpEntity<>(request, headers);

        try {
            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    baseUrl + "/" + id + "/runs",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            // 응답 바디 가져오기
            String responseBody = responseEntity.getBody();

            // JSON을 Map으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            AiRunsResponse responseMap = objectMapper.readValue(responseBody, AiRunsResponse.class);

            // 응답 처리
            log.info("Response: " + responseMap);
            return responseMap;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return null;
        }
    }

    public AiRunsResponse runsOneSelect(String thread_id, String run_id) {
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v2");

        // 요청 엔티티 생성
        HttpEntity<AiRunsRequest> httpEntity = new HttpEntity<>(headers);

        try {
            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    baseUrl + "/" + thread_id + "/runs/" + run_id,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );

            // 응답 바디 가져오기
            String responseBody = responseEntity.getBody();

            // JSON을 Map으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            AiRunsResponse responseMap = objectMapper.readValue(responseBody, AiRunsResponse.class);

            // 응답 처리
            log.info("Response: " + responseMap);
            return responseMap;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return null;
        }
    }
}
