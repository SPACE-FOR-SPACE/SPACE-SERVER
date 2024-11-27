package com.space.server.domain.ai.service.implementation;

import com.space.server.domain.chat.domain.value.Type;
import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.checklist.domain.Checklist;
import com.space.server.domain.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PromptCreator {

    public String create(Type type, Quiz quiz, List<Checklist> checklists, Chapter chapter, String chat, Long userAge) {
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
            "입력은 '" + chat + "', 유저의 나이는 '" + userAge + "'" +
            "일 때 정답인지 아닌지와 문제 조건들 중에서 어떤 것들이 맞는지 리스트로 나타내고," +
            "입력에 대한 피드백과 움직임을 JSON 형태로 만들어. " +
            "결과를 ```json```으로 감싸. 그리고 응답이 왜 그런지도 설명해. 응답이 왜 그런지 설명할 때 처음부터 캐릭터의 방향을 계산하는 것을 먼저 말해." +
            "동작을 하나 처리한 뒤에 해당 동작을 처리한 뒤의 방향을 바탕으로 다음 동작을 동서남북에 맞게 변환해야돼. 고민하거나 둘러보는 것은 동작에 반영하지 않아야 돼.";
    }
}
