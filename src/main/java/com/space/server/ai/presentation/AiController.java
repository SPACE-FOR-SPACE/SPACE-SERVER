package com.space.server.ai.presentation;

import com.space.server.ai.service.dto.request.gpt.AiChat;
import com.space.server.ai.service.dto.request.AiCodeRequest;
import com.space.server.ai.service.dto.request.gpt.AiAssistantsRequest;
import com.space.server.ai.service.dto.request.ResponseFormat;
import com.space.server.ai.service.implementation.ChatCompleter;
import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.chapter.service.QueryChapterService;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.checklist.service.QueryChecklistService;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.service.QueryQuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
    private final QueryQuizService queryQuizService;
    private final QueryChapterService queryChapterService;
    private final QueryChecklistService queryChecklistService;
    private final ChatCompleter chatCompleter;

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
        ChatCompleter chatCompleter = new ChatCompleter();

        // AI 연결 코드
        ArrayList<AiChat> aiChats = new ArrayList<>();
        aiChats.add(new AiChat("system", "The response format is JSON."));
        aiChats.add(new AiChat("system", prompt));
        log.warn("AI Result aiChats :" + aiChats);
        AiAssistantsRequest aiRequest = new AiAssistantsRequest("llama-3.1-8b-instant", aiChats, 1F, 2048L, 1L, false, new ResponseFormat("json_object"), null);
        log.warn("AI Result aiRequest :" + aiRequest);

        // AI 요청 비동기 처리
        chatCompleter.completerChat(aiRequest);

        // AI 저장 코드
        // AI 반환 코드
    }
}
