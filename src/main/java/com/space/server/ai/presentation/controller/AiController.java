package com.space.server.ai.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.server.ai.presentation.dto.request.AiChat;
import com.space.server.ai.presentation.dto.request.AiCodeRequest;
import com.space.server.ai.presentation.dto.request.AiRequest;
import com.space.server.ai.presentation.dto.request.ResponseFormat;
import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.chapter.service.QueryChapterService;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.checklist.service.QueryChecklistService;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.service.QueryQuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {

    @Value("${groq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final QueryQuizService queryQuizService;
    private final QueryChapterService queryChapterService;
    private final QueryChecklistService queryChecklistService;

    @PostMapping("/result")
    public void aiResult(@RequestBody AiCodeRequest aiCodeRequest) {
        Quiz quiz = queryQuizService.findOne(aiCodeRequest.quizId(), aiCodeRequest.chapterId());
        Chapter chapter = queryChapterService.readOne(aiCodeRequest.chapterId());
        List<Checklist> checklist = queryChecklistService.findByQuiz(aiCodeRequest.quizId());


        String prompt = "상태는 'CODE', " +
                "문제 난이도는 '0', " +
                "문제 내용은 '"+ quiz.getContent() +"', " +
                "문제 조건들은 '"+ checklist +"', " +
                "맵은 '"+ quiz.getMap() +"', " +
                "맵의 오브젝트는 '" + chapter.getMapObject() + "', " + // quiz쪽도 조회해서 합쳐주세요.
                "캐릭터 방향은 '"+ quiz.getCharacterDirection() +"', " +
                "입력은 '" + aiCodeRequest.code() + "'" +
                "일 때 정답인지 아닌지와 문제 조건들의 맞는 퍼센트를 백분율로 나타내고, 피드백과 움직임을 나타낸 맵을 JSON 형태로 만들어줘";

        // AI 연결 코드
        ArrayList<AiChat> aiChats = new ArrayList<>();
        aiChats.add(new AiChat("system", "The response format is JSON."));
        aiChats.add(new AiChat("system", prompt));
        log.warn("AI Result aiChats :" + aiChats);
        AiRequest aiRequest = new AiRequest("llama-3.1-8b-instant", aiChats, 1F, 2048L, 1L, false, new ResponseFormat("json_object"), null);
        log.warn("AI Result aiRequest :" + aiRequest);

        // AI 요청 비동기 처리
        createChatCompletion(aiRequest);

        // AI 저장 코드
        // AI 반환 코드
    }


    public void createChatCompletion(AiRequest request) {
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
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

            // 응답 처리
            log.info("Response: " + responseMap);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }
    }
}
