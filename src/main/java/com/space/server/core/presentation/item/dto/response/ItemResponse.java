package com.space.server.core.presentation.item.dto.response;

import com.space.server.core.domain.item.Item;
import com.space.server.core.domain.item.value.Category;
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
