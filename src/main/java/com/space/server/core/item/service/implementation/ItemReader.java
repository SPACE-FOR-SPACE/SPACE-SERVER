package com.space.server.core.item.service.implementation;

import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import com.space.server.core.item.domain.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemReader {

  private final ItemRepository itemRepository;

  public Item findById(Long itemId) {
    return itemRepository.findById(itemId)
        .orElseThrow(() -> new SpaceException(ErrorCode.ITEM_NOT_FOUND));
  }

  public List<Item> findAll() {
    return itemRepository.findAll();
  }

  public List<Item> findAllByCategory(Category category) {
    return itemRepository.findByCategory(category);
  }
}
