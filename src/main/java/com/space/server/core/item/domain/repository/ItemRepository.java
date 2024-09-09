package com.space.server.core.item.domain.repository;

import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
  public List<Item> findByCategory(Category category);
}
