package com.space.server.domain.inventory.adapter.in.web.dto.response;

import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.domain.value.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "인벤토리 응답 DTO")
public record InventoryResponse(
    @Schema(description = "인벤토리 ID", example = "1")
    Long id,

    String name,

    @Schema(description = "아이템 ID", example = "10")
    Long itemId,

    @Schema(description = "아이템 카테고리", example = "HEAD")
    Category category,

    @Schema(description = "장착 여부", example = "true")
    boolean isEquipped
) {
  public static InventoryResponse from(Inventory inventory) {
    return InventoryResponse.builder()
        .id(inventory.getId().getValue())
        .name(inventory.getItem().getName())
        .itemId(inventory.getItem().getId().getValue())
        .category(inventory.getItem().getCategory())
        .isEquipped(inventory.isEquipped())
        .build();
  }
}
