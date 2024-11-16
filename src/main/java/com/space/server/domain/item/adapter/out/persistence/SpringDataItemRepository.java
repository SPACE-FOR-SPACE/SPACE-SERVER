package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.domain.item.domain.value.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataItemRepository extends JpaRepository<ItemJpaEntity, Long> {
  List<ItemJpaEntity> findByCategory(Category category);

}
