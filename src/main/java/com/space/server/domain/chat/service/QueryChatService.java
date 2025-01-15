package com.space.server.domain.chat.service;

import com.space.server.domain.chat.domain.Chat;
import com.space.server.domain.chat.presentation.dto.response.ChatResponse;
import com.space.server.domain.chat.presentation.dto.response.VocabularyFeedbackResponse;
import com.space.server.domain.chat.service.implementation.ChatAnalyzer;
import com.space.server.domain.chat.service.implementation.ChatReader;
import com.space.server.domain.quiz.service.implementation.QuizReader;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.exception.StateNotFoundException;
import com.space.server.domain.state.service.implementation.StateReader;
import com.space.server.domain.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryChatService {

    private final OpenAiChatClient chatClient;
    private final ChatReader chatReader;
    private final StateReader stateReader;
    private final QuizReader quizReader;
    private final UserReader userReader;
    private final ChatAnalyzer chatAnalyzer;

    public List<ChatResponse> readChats(Long quizId, Long userId) {
        List<Chat> chatList = chatReader.findAllChatByState(stateReader.findByQuizIdAndUserId(
            quizReader.findById(quizId),
            userReader.findById(userId)
        ).orElseThrow(StateNotFoundException::new));

        return chatList.stream()
            .map(chat -> new ChatResponse(chat.getRequest_order(), chat.getBotChat(), chat.getUserChat()))
            .collect(Collectors.toList());
    }

    public Map<String, Long> readMostKeyWords(Long userId) {
        List<State> states = stateReader.findByUserId(userReader.findById(userId));

        List<Chat> chats = chatReader.findAllChatByStates(states);

        Map<String, Long> frequencyMap = chats.stream()
            .flatMap(chat -> chatAnalyzer.analyzeText(chat.getUserChat()).stream())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return frequencyMap.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
    }

    public VocabularyFeedbackResponse findTypo(Long userId) {
        // 사용자의 상태와 채팅 내용 조회
        List<State> states = stateReader.findByUserId(userReader.findById(userId));
        List<Chat> chats = chatReader.findAllChatByStates(states);

        // 모든 채팅 내용 병합
        String mergedChats = chats.stream()
                .map(Chat::getUserChat)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));

        // 어휘 오류 분석 프롬프트
        String analysisPrompt = String.format(
                "다음 텍스트에서 가장 많이 틀린 어휘 상위 5개를 찾아주세요. " +
                        "각 어휘에 대해 몇 번 잘못 썼는지, 올바른 표현은 무엇인지 알려주세요. " +
                        "초등학생의 글입니다. 텍스트: %s", mergedChats
        );

        // 어휘 오류 분석
        String analysisResponse = chatClient.call(analysisPrompt);

        // 피드백 생성 프롬프트
        String feedbackPrompt = String.format(
                "다음은 초등학생의 어휘 사용 분석입니다: %s\n\n" +
                        "이 분석을 바탕으로 부모님과 선생님을 위한 상세하고 구체적인 어휘력 향상 피드백을 만들어주세요. " +
                        "다음 사항을 포함해주세요:\n" +
                        "1. 발견된 어휘 오류의 구체적인 교정 방법\n" +
                        "2. 학생의 어휘력을 높이기 위한 맞춤형 학습 전략\n" +
                        "3. 연령에 적합한 어휘 학습 접근 방식\n" +
                        "4. 부모님과 선생님이 함께할 수 있는 실천 가능한 활동\n" +
                        "5. 장기적인 어휘력 향상을 위한 지속 가능한 방법",
                analysisResponse
        );

        // AI 생성 피드백 요청
        String aiGeneratedFeedback = chatClient.call(feedbackPrompt);

        // 결과 객체 생성
        VocabularyFeedbackResponse feedback = new VocabularyFeedbackResponse(analysisResponse, aiGeneratedFeedback);

        return feedback;
    }



    public Integer countChats(Long quizId, Long userId) {
        State state = stateReader.findByQuizIdAndUserId(quizReader.findById(quizId), userReader.findById(userId))
            .orElseThrow(StateNotFoundException::new);

        return chatReader.countChatByQuiz(state);
    }
}
