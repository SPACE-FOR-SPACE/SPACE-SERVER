package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.domain.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

  public Item mapToItem(ItemJpaEntity item) {
    return Item.builder()
        .id(new Item.ItemId(item.getId()))
        .name(item.getName())
        .price(item.getPrice())
        .image(item.getImage())
        .category(item.getCategory())
        .build();
  }

  public ItemJpaEntity mapToItemJpaEntity(Item item) {
    return ItemJpaEntity.builder()
        .id(item.getId().getValue())
        .name(item.getName())
        .price(item.getPrice())
        .image(item.getImage())
        .category(item.getCategory())
        .build();
  }
}
