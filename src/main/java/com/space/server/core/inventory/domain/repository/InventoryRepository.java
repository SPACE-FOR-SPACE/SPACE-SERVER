package com.space.server.core.inventory.domain.repository;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import com.space.server.core.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  List<Inventory> findByUser(Users user);
  Boolean existsByItemAndUser(Item item, Users user);
  @Query("SELECT i FROM Inventory i WHERE i.user = :user AND i.isEquipped = true")
  List<Inventory> findByIsEquippedAndUser(@Param("user") Users user);
  @Query("SELECT i FROM Inventory i WHERE i.user = :user AND i.item.category = :category AND i.isEquipped = true")
  Inventory findByCategoryAndUserAndIsEquipped(@Param("category") Category category, @Param("user") Users user);
}
