package com.space.server.ai.presentation.controller;

import com.space.server.ai.presentation.dto.request.AiCodeRequest;
import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.chapter.service.QueryChapterService;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.checklist.service.QueryChecklistService;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.service.QueryQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
    private final QueryQuizService queryQuizService;
    private final QueryChapterService queryChapterService;
    private final QueryChecklistService queryChecklistService;
    // CommandService들 연결

    @PostMapping("/result")
    public void aiResult(@RequestBody AiCodeRequest aiCodeRequest){
        Quiz quiz = queryQuizService.findOne(aiCodeRequest.chapterId(), aiCodeRequest.quizId());
        List<Checklist> checklist = queryChecklistService.findByQuiz(aiCodeRequest.quizId());
        Chapter chapter = queryChapterService.readOne(aiCodeRequest.chapterId());

        String prompt = "상태는 'CODE', " +
                        "문제 난이도는 '0', " +
                        "문제 내용은 '"+ quiz.getContent() +"', " +
                        "문제 조건들은 '"+ checklist +"', " +
                        "맵은 '"+ quiz.getMap() +"', " +
                        "맵의 오브젝트는 '"+ chapter.getMapObject() +"', " +
                        "캐릭터 방향은 '"+ quiz.getCharacterDirection() +"', " +
                        "입력은 '"+ aiCodeRequest.code() +"'" +
                        "일 때 정답인지 아닌지와 문제 조건들의 맞는 퍼센트를 백분율로 나타내고, 피드백과 움직임을 나타낸 맵을 JSON 형태로 만들어줘";

        //AI 연결 코드
        //결과 저장 코드
        //결과 반환 코드
    }
}
