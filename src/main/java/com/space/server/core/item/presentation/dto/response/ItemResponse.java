package com.space.server.core.item.presentation.dto.response;

import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import lombok.Builder;

@Builder
public record ItemResponse(
    Long id,
    String name,
    int price,
    String image,
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
