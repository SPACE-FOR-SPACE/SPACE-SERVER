package com.space.server.core.item.domain.value;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "아이템의 카테고리")
public enum Category {
  @Schema(description = "머리")
  HEAD,

  @Schema(description = "테마")
  THEME
}
