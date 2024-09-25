package com.space.server.core.inventory.service;

import com.space.server.auth.service.dto.CustomUserDetails;
import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryReader;
import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

  public List<Inventory> readMine(Authentication auth) {
    CustomUserDetails details = (CustomUserDetails) auth.getPrincipal();
    Users user = userRepository.findByEmail(details.getEmail());
    return inventoryReader.findByUser(user);
  }

  public List<Inventory> readIsEquipped(Authentication auth) {
    CustomUserDetails details = (CustomUserDetails) auth.getPrincipal();
    Users user = userRepository.findByEmail(details.getEmail());
    return inventoryReader.findByIsEquippedAndUser(user);
  }
}
