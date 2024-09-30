package com.space.server.state.presentation.dto.response;

import com.space.server.core.quiz.domain.Quiz;
import com.space.server.state.domain.State;
import com.space.server.state.domain.value.Status;
import com.space.server.user.domain.Users;

public record StateResponse(
        Long stateId,
        Users userId,
        Quiz quizId,
        Status status,
        Integer[][] map,
        Double score
) {
    public static StateResponse from(State state) {
        return new StateResponse(
                state.getId(),
                state.getUser(),
                state.getQuiz(),
                state.getStatus(),
                state.getMap(),
                state.getScore()
        );
    }
}
