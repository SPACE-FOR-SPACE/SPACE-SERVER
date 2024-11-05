package com.space.server.domain.chat.service;

import com.space.server.domain.chat.domain.Chat;
import com.space.server.domain.chat.presentation.dto.response.ChatResponse;
import com.space.server.domain.chat.service.implementation.ChatReader;
import com.space.server.domain.quiz.service.implementation.QuizReader;
import com.space.server.state.service.implementation.StateReader;
import com.space.server.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryChatService {

    private final ChatReader chatReader;
    private final StateReader stateReader;
    private final QuizReader quizReader;
    private final UserReader userReader;

    public List<ChatResponse> readChats(Long quizId, Long userId){
        List<Chat> chatList = chatReader.findAllChatByState(stateReader.findByQuizIdAndUserId(
                quizReader.findById(quizId),
                userReader.findById(userId)
        ).get());
        List<ChatResponse> chatResponseList = new ArrayList<>();

        for (Chat chat : chatList) {
            chatResponseList.add(new ChatResponse(chat.getRequest_order(), chat.getBotChat(), chat.getUserChat()));
        }

        return chatResponseList;
    }

}
