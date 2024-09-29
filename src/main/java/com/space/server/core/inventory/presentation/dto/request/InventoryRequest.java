package com.space.server.core.inventory.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인벤토리 요청 DTO")
public record InventoryRequest(

    @Schema(description = "아이템 ID", example = "1")
    Long itemId
) {}
