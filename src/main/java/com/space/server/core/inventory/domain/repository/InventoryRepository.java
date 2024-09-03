package com.space.server.core.inventory.domain.repository;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.Item;
import com.space.server.core.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  List<Inventory> findByUser(User user);
  Boolean findByUserAndItem(User user, Item item);
}
