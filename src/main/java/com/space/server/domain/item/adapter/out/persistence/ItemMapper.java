package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.domain.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

  Item mapToDomainEntity(ItemJpaEntity item) {
    return Item.create(
        new Item.ItemId(item.getId()),
        item.getName(),
        item.getPrice(),
        item.getImage(),
        item.getCategory()
    );
  }
}
