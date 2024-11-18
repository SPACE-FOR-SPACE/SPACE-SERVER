package com.space.server.domain.item.adapter.in.web.dto.response;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.value.Category;
import lombok.Builder;

@Builder
public record ItemResponse(
    String name,
    Integer price,
    String image,
    Category category
) {
  public static ItemResponse from(Item item) {
    return ItemResponse.builder()
        .name(item.getName())
        .price(item.getPrice())
        .image(item.getImage())
        .category(item.getCategory())
        .build();
  }
}
