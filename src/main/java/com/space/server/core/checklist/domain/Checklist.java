package com.space.server.core.checklist.domain;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Checklist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @NotNull
  private Quiz quiz;

  @ManyToOne
  @NotNull
  private Chapter chapter;

  @NotNull
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
