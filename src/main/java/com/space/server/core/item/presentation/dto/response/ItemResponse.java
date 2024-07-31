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
  public static ItemResponse of(Item item) {
    return new ItemResponse(item.getId(), item.getName(), item.getPrice(), item.getImage(), item.getCategory());
  }
}
