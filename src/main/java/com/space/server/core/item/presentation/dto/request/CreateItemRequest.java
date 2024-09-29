package com.space.server.core.item.presentation.dto.request;

import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "아이템 생성 요청 DTO")
public record CreateItemRequest(

    @Schema(description = "아이템 이름", example = "모자")
    String name,

    @Schema(description = "아이템 가격", example = "100")
    Integer price,

    @Schema(description = "아이템 이미지 URL", example = "http://example/image.png")
    String image,

    @Schema(description = "아이템 카테고리", example = "HEAD")
    Category category
) {
  public Item toEntity() {
    return new Item(name, price, image, category);
  }
}
