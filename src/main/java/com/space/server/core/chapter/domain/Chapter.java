package com.space.server.core.chapter.domain;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLHStoreType;
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
public class Chapter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String explanation;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  @NotNull
  private Map<String, String> mapObject;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  @NotNull
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
