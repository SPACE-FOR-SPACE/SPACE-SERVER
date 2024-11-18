package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.inventory.application.port.out.CheckHaveItemPort;
import com.space.server.domain.item.adapter.out.persistence.ItemJpaEntity;
import com.space.server.domain.item.adapter.out.persistence.ItemMapper;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.user.domain.Users;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CheckHaveItemAdapter implements CheckHaveItemPort {

  private final InventoryRepository inventoryRepository;
  private final ItemMapper itemMapper;

  @Override
  public Boolean checkHaveItem(Item item, Users user) {
    ItemJpaEntity itemJpaEntity = itemMapper.mapToItemJpaEntity(item);
    return inventoryRepository.existsByItemAndUser(itemJpaEntity, user);
  }
}
