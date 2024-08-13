package com.space.server.core.item.presentation.dto.request;

import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;

public record UpdateItemRequest(
    String name,
    int price,
    String image,
    Category category
) {
  public Item toEntity() {
    return new Item(name,price,image,category);
  }
}
