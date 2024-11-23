package com.space.server.domain.chat.presentation.dto.response;

import java.util.List;

public record ReadKeyWordsResponse(
    Long userId,
    Long quizId,
    List<String> keyWords
) {
  public static ReadKeyWordsResponse of(Long userId, Long quizId, List<String> keyWords) {
    return new ReadKeyWordsResponse(userId, quizId, keyWords);
  }
}
