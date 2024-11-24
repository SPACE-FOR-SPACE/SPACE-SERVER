package com.space.server.domain.state.service;

import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.service.implementation.QuizReader;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.exception.StateNotSuccessException;
import com.space.server.domain.state.service.implementation.StateReader;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryStateService {

    private final StateReader stateReader;
    private final QuizReader quizReader;
    private final UserReader userReader;

    public State readOne(Long stateId) {
        return stateReader.findById(stateId);
    }

    public State findSuccessByQuizIdAndUserId(Long quizId, Long userId) {
        Quiz quiz = quizReader.findById(quizId);
        Users user = userReader.findById(userId);
        State state = stateReader.findByQuizIdAndUserId(quiz, user)
            .orElseThrow(StateNotSuccessException::new);
        if(state.getFirstTime() == null) throw new StateNotSuccessException();
        else return state;
    }
}
