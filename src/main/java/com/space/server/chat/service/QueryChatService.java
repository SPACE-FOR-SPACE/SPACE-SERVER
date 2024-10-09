package com.space.server.chat.service;

import com.space.server.chat.domain.Chat;
import com.space.server.chat.service.implementation.ChatReader;
import com.space.server.core.quiz.service.implementation.QuizReader;
import com.space.server.state.service.implementation.StateReader;
import com.space.server.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryChatService {

    private final ChatReader chatReader;
    private final StateReader stateReader;
    private final QuizReader quizReader;
    private final UserReader userReader;

    public List<Chat> readChats(Long quizId, Long userId){
        return chatReader.findAllChatByState(
                stateReader.findByQuizIdAndUserId(
                        quizReader.findById(quizId),
                        userReader.findById(userId)
                ).get()
        );
    }

}
