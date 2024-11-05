package com.space.server.domain.quiz.domain;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.quiz.domain.value.CharacterDirection;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLHStoreType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Schema(description = "퀴즈 Entity")
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "퀴즈 ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  @Schema(description = "챕터 정보")
  private Chapter chapter;

  @NotNull
  @Schema(description = "스텝 ID")
  private Long stepId;

  @NotNull
  @Schema(description = "퀴즈 제목")
  private String title;

  @NotNull
  @Schema(description = "퀴즈 내용")
  private String content;

  @Type(IntArrayType.class)
  @Column(columnDefinition = "int[][]")
  @NotNull
  @Schema(description = "퀴즈 맵 (7x7)")
  private Integer[][] map;

  @Enumerated(EnumType.STRING)
  @NotNull
  @Schema(description = "캐릭터 방향")
  private CharacterDirection characterDirection;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  @NotNull
  @Schema(description = "맵 오브젝트 정보")
  private Map<String, String> mapObject;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  @NotNull
  @Schema(description = "맵 오브젝트 이미지 정보")
  private Map<String, String> mapObjectImage;

  @Builder
  public Quiz(Chapter chapter, Long stepId, String title, String content, Integer[][] map, CharacterDirection characterDirection, Map<String, String> mapObject, Map<String, String> mapObjectImage) {
    this.chapter = chapter;
    this.stepId = stepId;
    this.title = title;
    this.content = content;
    this.map = map;
    this.characterDirection = characterDirection;
    this.mapObject = mapObject;
    this.mapObjectImage = mapObjectImage;
  }

  @Builder
  public Quiz(Integer[][] map, CharacterDirection characterDirection) {
    this.map = map;
    this.characterDirection = characterDirection;
  }

  public void update(Quiz quiz) {
    if (quiz.getMap() != null) {
      this.map = new Integer[7][7];
      for (int i = 0; i < 7; i++) {
        System.arraycopy(quiz.getMap()[i], 0, this.map[i], 0, 7);
      }
    }
    this.characterDirection = quiz.getCharacterDirection();
  }
}
