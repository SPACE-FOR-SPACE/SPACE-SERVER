package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.item.application.port.out.LoadItemPort;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ItemGetAdapter implements LoadItemPort {

  private final ItemRepository itemRepository;
  private final ItemMapper itemMapper;

  @Override
  public Item loadItem(Long id) {
    return itemRepository.findById(id)
        .map(itemMapper::mapToItem)
        .orElseThrow(ItemNotFoundException::new);
  }
}