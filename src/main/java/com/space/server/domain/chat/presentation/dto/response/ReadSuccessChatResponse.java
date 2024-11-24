package com.space.server.domain.chat.presentation.dto.response;

import com.space.server.domain.state.domain.State;

import java.time.LocalDateTime;

public record ReadSuccessChatResponse(
    Long userId,
    Long quizId,
    LocalDateTime firstTime,
    LocalDateTime lastTime,
    Integer successCount
) {
  public static ReadSuccessChatResponse from(State state) {
    return new ReadSuccessChatResponse(state.getUser().getId(),
        state.getQuiz().getId(),
        state.getFirstTime(),
        state.getLastTime(),
        state.getSuccessCount()
    );
  }
}
