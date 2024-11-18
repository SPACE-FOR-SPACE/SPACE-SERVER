package com.space.server.domain.checklist.domain;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.quiz.domain.Quiz;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Checklist {

  @NotNull
  private ChecklistId id;

  @NotNull
  private Quiz quiz;

  @NotNull
  private Chapter chapter;

  @NotNull
  private String content;

  @Value
  public static class ChecklistId {
    private final Long value;
  }
}
