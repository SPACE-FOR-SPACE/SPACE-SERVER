package com.space.server.core.quiz.domain.value;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "캐릭터 이동 방향")
public enum CharacterDirection {

  @Schema(description = "오른쪽 방향")
  RIGHT,

  @Schema(description = "왼쪽 방향")
  LEFT,

  @Schema(description = "위쪽 방향")
  UP,

  @Schema(description = "아래쪽 방향")
  DOWN
}
