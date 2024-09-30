package com.space.server.ai.service.implementation;

import com.space.server.chat.domain.Chat;
import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.state.domain.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromptCreator {

    public String create(String state, Quiz quiz, List<Checklist> checklists, Chapter chapter, String chat) {
        return "상태는 '"+ state +"', " +
            "문제 난이도는 '0', " +
            "문제 내용은 '"+ quiz.getContent() +"', " +
            "문제 조건들은 '"+ checklists +"', " +
            "맵은 '"+ quiz.getMap() +"', " +
            "맵의 오브젝트는 '" + chapter.getMapObject() + "', " + // quiz쪽도 조회해서 합쳐주세요.
            "캐릭터 방향은 '"+ quiz.getCharacterDirection() +"', " +
            "입력은 '" + chat + "'" +
            "일 때 정답인지 아닌지와 문제 조건들의 맞는 퍼센트를 백분율로 나타내고, 피드백과 움직임을 나타낸 맵을 JSON 형태로 만들어줘";
    }
}
