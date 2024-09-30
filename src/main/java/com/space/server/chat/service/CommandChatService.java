package com.space.server.chat.service;

import com.space.server.chat.presentation.dto.request.CreateChatRequest;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.service.implementation.QuizReader;
import com.space.server.state.domain.State;
import com.space.server.state.service.implementation.StateReader;
import com.space.server.user.domain.Users;
import com.space.server.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandChatService {

    private final StateReader stateReader;
    private final QuizReader quizReader;
    private final UserReader userReader;

    public void create(Long quizId, CreateChatRequest request, Long userId) {
        Quiz quiz = quizReader.findById(quizId);
        Users user = userReader.findById(userId);

        Optional<State> state = stateReader.findByQuizIdAndUserId(quiz, user);

        // state 있다면 대화 로직
        if (state.isPresent()) {

        }
        // 없다면 처음 만드는 로직
        else {

        }
    }
}
