package com.space.server.core.quiz.domain;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.value.CharacterDirection;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

  @Type(IntArrayType.class)
  @Column(columnDefinition = "int[][]")
  @NotNull
  private Integer[][] map;

  @Enumerated(EnumType.STRING)
  @NotNull
  private CharacterDirection characterDirection;

  @Builder
  public Quiz(Chapter chapter, Long stepId, Long npcId, String title, String content, Integer[][] map, CharacterDirection characterDirection) {
    this.chapter = chapter;
    this.stepId = stepId;
    this.npcId = npcId;
    this.title = title;
    this.content = content;
    this.map = map;
    this.characterDirection = characterDirection;
  }

  @Builder
  public Quiz(Integer[][] map, CharacterDirection characterDirection) {
    this.map = map;
    this.characterDirection = characterDirection;
  }

  public void update(Quiz quiz) {
    this.map = quiz.getMap().clone();
    this.characterDirection = quiz.getCharacterDirection();
  }
}
