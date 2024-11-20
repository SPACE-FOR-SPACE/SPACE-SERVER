package com.space.server.domain.quiz.domain;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.quiz.domain.value.CharacterDirection;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Quiz {

  private QuizId id;

  @NotNull
  private Chapter chapter;

  @NotNull
  private Long stepId;

  @NotNull
  private String title;

  @NotNull
  private String content;

  @NotNull
  private Integer[][] map;

  @NotNull
  private CharacterDirection characterDirection;

  @NotNull
  private Map<String, String> mapObject;

  @NotNull
  private Map<String, String> mapObjectImage;

  @Value
  public static class QuizId {
    private final Long value;
  }
}
