package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.domain.item.domain.value.Category;
import com.space.server.domain.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryJpaEntity, Long> {
  List<InventoryJpaEntity> findByUser(Users user);
  @Query("SELECT i FROM InventoryJpaEntity i WHERE i.user = :user AND i.isEquipped = true")
  List<InventoryJpaEntity> findByIsEquippedAndUser(@Param("user") Users user);
  @Query("SELECT i FROM InventoryJpaEntity i WHERE i.user = :user AND i.item.category = :category AND i.isEquipped = true")
  InventoryJpaEntity findByCategoryAndUserAndIsEquipped(@Param("category") Category category, @Param("user") Users user);

}
