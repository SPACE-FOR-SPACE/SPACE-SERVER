package com.space.server.ai.service.implementation;

import com.space.server.chat.domain.value.Type;
import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PromptCreator {

    public String create(Type type, Quiz quiz, List<Checklist> checklists, Chapter chapter, String chat) {
        List<String> checklist = new ArrayList<>();
        for (Checklist cl : checklists) {
            checklist.add(cl.getContent());
        }

        Map<String, String> totalMapObject = new HashMap<>();
        totalMapObject.putAll(quiz.getMapObject());
        totalMapObject.putAll(chapter.getMapObject());

        return "상태는 '"+ type +"', " +
            "문제 난이도는 '0', " +
            "문제 내용은 '"+ quiz.getContent() +"', " +
            "문제 조건들은 '"+ checklist +"', 문제 조건 중에서 하나라도 맞으면 정답이야." +
            "맵은 '"+ Arrays.deepToString(quiz.getMap()) +"', 맵은 무조건 7*7 2차원 배열이야." +
            "맵의 오브젝트는 '" + totalMapObject + "', " +
            "캐릭터 방향은 '"+ quiz.getCharacterDirection() +"', " +
            "입력은 '" + chat + "'" +
            "일 때 정답인지 아닌지와 문제 조건들 중에서 어떤 것들이 맞는지 리스트로 나타내고," +
            "입력에 대한 피드백과 움직임과 움직임을 나타낸 맵을 JSON 형태로 만들어. " +
            "근데 결과 말고는 json으로 만들지 마. 그리고 응답이 왜 그런지도 설명해.";
    }
}
