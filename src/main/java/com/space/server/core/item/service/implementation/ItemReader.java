package com.space.server.core.item.service.implementation;

import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import com.space.server.core.item.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemReader {

  private final ItemRepository itemRepository;

  public List<Item> readAll() {
    return itemRepository.findAll();
  }

  public List<Item> readAllByCategory(String category) {
    return itemRepository.findByCategory(Category.valueOf(category));
  }

  public Item read(Long itemId) {
    return itemRepository.getById(itemId);
  }
}
