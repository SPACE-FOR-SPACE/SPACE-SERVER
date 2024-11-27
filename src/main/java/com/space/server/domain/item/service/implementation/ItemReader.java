package com.space.server.domain.item.service.implementation;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.value.Category;
import com.space.server.domain.item.domain.repository.ItemRepository;
import com.space.server.domain.item.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemReader {

  private final ItemRepository itemRepository;

  public Item findById(Long itemId) {
    return itemRepository.findById(itemId)
        .orElseThrow(ItemNotFoundException::new);
  }

  public Item findByName(String itemName) {
    return itemRepository.findByName(itemName);
  }

  public List<Item> findAll() {
    return itemRepository.findAll();
  }

  public List<Item> findAllByCategory(Category category) {
    return itemRepository.findByCategory(category);
  }
}
