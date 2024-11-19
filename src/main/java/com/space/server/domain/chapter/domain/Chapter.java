package com.space.server.domain.chapter.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Chapter {

  private ChapterId id;

  @NotNull
  private String explanation;

  @NotNull
  private Map<String, String> mapObject;

  @NotNull
  private Map<String, String> mapObjectImage;

  @Value
  public static class ChapterId {
    private final Long value;
  }
}
