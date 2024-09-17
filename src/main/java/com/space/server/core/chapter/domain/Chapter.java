package com.space.server.core.chapter.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chapter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String explanation;

//  @Type(type = "jsonb")
//  @Column(columnDefinition = "jsonb")
//  private Map<String, String> mapObject;

  @Builder
  public Chapter(String explanation) {
    this.explanation = explanation;
  }

  public void update(Chapter chapter) {
    this.explanation = chapter.getExplanation();
  }
}
