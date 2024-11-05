package com.space.server.domain.inventory.domain.repository;

import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.value.Category;
import com.space.server.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  List<Inventory> findByUser(Users user);
  Boolean existsByItemAndUser(Item item, Users user);
  @Query("SELECT i FROM Inventory i WHERE i.user = :user AND i.isEquipped = true")
  List<Inventory> findByIsEquippedAndUser(@Param("user") Users user);
  @Query("SELECT i FROM Inventory i WHERE i.user = :user AND i.item.category = :category AND i.isEquipped = true")
  Inventory findByCategoryAndUserAndIsEquipped(@Param("category") Category category, @Param("user") Users user);
}
