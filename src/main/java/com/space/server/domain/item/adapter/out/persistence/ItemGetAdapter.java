package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.domain.common.PersistenceAdapter;
import com.space.server.domain.item.application.port.out.LoadItemPort;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ItemGetAdapter implements LoadItemPort {

  private final SpringDataItemRepository itemRepository;
  private final ItemMapper itemMapper;

  @Override
  public Item loadItem(Long id) {
    return itemRepository.findById(id)
        .map(itemMapper::mapToDomainEntity)
        .orElseThrow(ItemNotFoundException::new);
  }
}
