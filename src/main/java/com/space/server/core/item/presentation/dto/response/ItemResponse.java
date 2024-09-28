package com.space.server.core.item.presentation.dto.response;

import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ItemResponse(
    @Schema(description = "아이템 ID", example = "1")
    Long id,

    @Schema(description = "아이템 이름", example = "모자")
    String name,

    @Schema(description = "아이템 가격", example = "100")
    Integer price,

    @Schema(description = "아이템 이미지 URL", example = "http://example.com/image.png")
    String image,

    @Schema(description = "아이템 카테고리", example = "HEAD")
    Category category
) {
  public static ItemResponse from(Item item) {
    return ItemResponse.builder()
        .id(item.getId())
        .name(item.getName())
        .price(item.getPrice())
        .image(item.getImage())
        .category(item.getCategory())
        .build();
  }
}
