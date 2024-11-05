package com.space.server.domain.chapter.domain;

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
@Schema(description = "챕터 Entity")
public class Chapter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "챕터 ID")
  private Long id;

  @NotNull
  @Schema(description = "챕터 설명")
  private String explanation;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  @NotNull
  @Schema(description = "맵오브젝트")
  private Map<String, String> mapObject;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  @NotNull
  @Schema(description = "맵오브젝트 이미지")
  private Map<String, String> mapObjectImage;

  @Builder
  public Chapter(String explanation, Map<String, String> mapObject, Map<String, String> mapObjectImage) {
    this.explanation = explanation;
    this.mapObject = mapObject;
    this.mapObjectImage = mapObjectImage;
  }

  public void update(Chapter chapter) {
    this.explanation = chapter.getExplanation();
    this.mapObject = chapter.getMapObject();
  }
}
