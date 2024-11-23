package com.space.server.domain.chat.presentation.dto.response;

public record CountChatResponse(
    Long userId,
    Long quizId,
    Integer countChat
) {
  public static CountChatResponse of(Long userId, Long quizId, Integer countChat) {
    return new CountChatResponse(userId, quizId, countChat);
  }
}
