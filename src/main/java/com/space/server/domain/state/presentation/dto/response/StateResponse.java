package com.space.server.domain.state.presentation.dto.response;

import com.space.server.domain.quiz.adapter.out.persistence.QuizJpaEntity;
import com.space.server.domain.state.domain.value.Status;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.user.domain.Users;

public record StateResponse(
        Long stateId,
        Users userId,
        QuizJpaEntity quizId,
        Status status,
        Long[] score
) {
    public static StateResponse from(State state) {
        return new StateResponse(
                state.getId(),
                state.getUser(),
                state.getQuiz(),
                state.getStatus(),
                state.getScore()
        );
    }
}
