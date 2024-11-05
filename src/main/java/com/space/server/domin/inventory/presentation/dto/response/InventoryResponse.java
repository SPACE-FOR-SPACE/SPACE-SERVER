package com.space.server.domin.inventory.presentation.dto.response;

import com.space.server.domin.inventory.domain.Inventory;
import com.space.server.domin.item.domain.value.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "인벤토리 응답 DTO")
public record InventoryResponse(
    @Schema(description = "인벤토리 ID", example = "1")
    Long id,

    @Schema(description = "아이템 ID", example = "10")
    Long itemId,

    @Schema(description = "아이템 카테고리", example = "HEAD")
    Category category,

    @Schema(description = "장착 여부", example = "true")
    boolean isEquipped
) {
  public static InventoryResponse from(Inventory inventory) {
    return InventoryResponse.builder()
        .id(inventory.getId())
        .itemId(inventory.getItem().getId())
        .category(inventory.getItem().getCategory())
        .isEquipped(inventory.isEquipped())
        .build();
  }
}
