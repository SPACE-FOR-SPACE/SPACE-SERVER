package com.space.server.domain.inventory.service;

import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.inventory.service.implementation.InventoryReader;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryInventoryService {

  private final InventoryReader inventoryReader;
  private final UserRepository userRepository;

  public Inventory readOne(Long inventoryId) {
    return inventoryReader.findById(inventoryId);
  }

  public List<Inventory> readMine(Long userId) {
    Users user = userRepository.findById(userId)
        .orElse(null);
    return inventoryReader.findByUser(user);
  }

  public List<Inventory> readIsEquipped(Long userId) {
    Users user = userRepository.findById(userId)
        .orElse(null);
    return inventoryReader.findByIsEquippedAndUser(user);
  }
}
