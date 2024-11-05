package com.space.server.domain.item.service.implementation;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCreator {

  private final ItemRepository itemRepository;

  public void create(Item item) {
    itemRepository.save(item);
  }
}
