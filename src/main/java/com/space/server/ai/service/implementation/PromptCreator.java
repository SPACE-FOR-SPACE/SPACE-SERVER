package com.space.server.ai.service.implementation;

import com.space.server.chat.domain.Chat;
import com.space.server.chat.domain.value.Type;
import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.state.domain.State;
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
            "문제 조건들은 '"+ checklist +"', " +
            "입력에서 이 문제 조건들을 충족하고 있는지 혹은 입력 결과로 문제 조건들을 충족시키고 있는지를 판단했을 때 문제 조건 중 하나만 맞아도 정답이야." +
            "맵은 '"+ Arrays.deepToString(quiz.getMap()) +"', 맵은 무조건 7*7 2차원 배열이야." +
            "맵의 오브젝트는 '" + totalMapObject + "', " +
            "캐릭터 방향은 '"+ quiz.getCharacterDirection() +"', " +
            "입력은 '" + chat + "'" +
            "일 때 입력이 문제 조건들 중 하나라도 충족시키면 true고 아니면 false야. 그리고 입력을 통해서 행동한 후의 결과로도 문제 조건들 중에 하나라도 충족시키면 true이고 아니면 false야." +
            "입력이 문제 조건들을 몇 개가 맞는지 백분율로 나타내고(ex.4개 중에 1개가 맞으면 25 이렇게), " +
            "입력에 대한 피드백과 움직임과 움직임을 나타낸 맵을 JSON 형태로 만들어줘. " +
            "움직임은 맵 오브젝트와 입력을 참고해서 만드는데, 캐릭터가 이동하는 것은 'r': right, 'l': left, 'd': down, 'u': up 이렇게 4가지로 나타내줘." +
            "그리고 반환하는 맵에는 만든 움직임과 맵 오브젝트를 바탕으로 맵에 표시해줘. 캐릭터가 지나간 곳은 4, 캐릭터가 지나가거나 이동한 것을 제외한 행동들을 한 곳은 5, 캐릭터는 0 등으로 맵에 표시해줘.";
    }
}
