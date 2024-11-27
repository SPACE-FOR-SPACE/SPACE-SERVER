package com.space.server.domain.chat.presentation.dto.response;

import java.util.Map;

public record ReadKeyWordsResponse(
    Long userId,
    Long quizId,
    Map<String, Long> keyWords
) {
  public static ReadKeyWordsResponse of(Long userId, Long quizId, Map<String, Long> keyWords) {
    return new ReadKeyWordsResponse(userId, quizId, keyWords);
  }
}
