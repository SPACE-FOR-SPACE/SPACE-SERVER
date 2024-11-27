package com.space.server.domain.state.presentation.dto.response;

import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.domain.value.Status;

public record CheckStateResponse(
    Long stateId,
    Long userId,
    Long quizId,
    Status status
) {
  public static CheckStateResponse from(State state) {
    return new CheckStateResponse(
        state.getId(),
        state.getUser().getId(),
        state.getQuiz().getId(),
        state.getStatus()
    );
  }
}
