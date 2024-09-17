package com.space.server.core.quiz.domain;


import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.value.CharacterDirection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @NotNull
  private Chapter chapter;

  @NotNull
  private Long stepId;

  @NotNull
  private Long npcId;

  @NotNull
  private String title;

  @NotNull
  private String content;

//  private Integer[][] map;

  @Enumerated(EnumType.STRING)
  @NotNull
  private CharacterDirection characterDirection;

  @Builder
  public Quiz(Chapter chapter, Long stepId, Long npcId, String title, String content, CharacterDirection characterDirection) {
    this.chapter = chapter;
    this.stepId = stepId;
    this.npcId = npcId;
    this.title = title;
    this.content = content;
    this.characterDirection = characterDirection;
  }

  @Builder
  public Quiz(CharacterDirection characterDirection) {
    this.characterDirection = characterDirection;
  }

  public void update(Quiz quiz) {
    this.characterDirection = quiz.getCharacterDirection();
  }
}
