package com.space.server.domain.chat.service;

import com.space.server.domain.chat.domain.Chat;
import com.space.server.domain.chat.presentation.dto.response.ChatResponse;
import com.space.server.domain.chat.service.implementation.ChatAnalyzer;
import com.space.server.domain.chat.service.implementation.ChatReader;
import com.space.server.domain.quiz.service.implementation.QuizReader;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.exception.StateNotFoundException;
import com.space.server.domain.state.service.implementation.StateReader;
import com.space.server.domain.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryChatService {

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

    public List<String> readMostKeyWords(Long quizId, Long userId) {
        State state = stateReader.findByQuizIdAndUserId(quizReader.findById(quizId), userReader.findById(userId))
            .orElseThrow(StateNotFoundException::new);

        List<Chat> chatList = chatReader.findAllChatByState(state);

        List<String> allKeywords = new ArrayList<>();
        for (Chat chat : chatList) {
            allKeywords.addAll(chatAnalyzer.analyzeText(chat.getUserChat()));
        }

        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String keyword : allKeywords) {
            frequencyMap.put(keyword, frequencyMap.getOrDefault(keyword, 0) + 1);
        }

        return frequencyMap.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
}
