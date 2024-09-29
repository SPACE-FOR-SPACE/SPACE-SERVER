package com.space.server.core.checklist.domain;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Schema(description = "체크리스트 Entity")
public class Checklist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "체크리스트 ID")
  private Long id;

  @ManyToOne
  @NotNull
  @Schema(description = "퀴즈", required = true)
  private Quiz quiz;

  @ManyToOne
  @NotNull
  @Schema(description = "챕터", required = true)
  private Chapter chapter;

  @NotNull
  @Schema(description = "체크리스트 내용")
  private String content;

  @Builder
  public Checklist(Quiz quiz, Chapter chapter, String content) {
    this.quiz = quiz;
    this.chapter = chapter;
    this.content = content;
  }

  public void update(Checklist checklist) {
    this.content = checklist.getContent();
  }
}
