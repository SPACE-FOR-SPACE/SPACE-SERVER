package com.space.server.domin.item.domain.repository;

import com.space.server.domin.item.domain.Item;
import com.space.server.domin.item.domain.value.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
  List<Item> findByCategory(Category category);
}
