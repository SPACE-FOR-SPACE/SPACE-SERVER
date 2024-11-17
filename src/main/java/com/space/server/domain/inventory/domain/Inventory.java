package com.space.server.domain.inventory.domain;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.user.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Inventory {

  @NotNull
  private final InventoryId id;

  @NotNull
  private final Users user;

  @NotNull
  private final Item item;

  @NotNull
  private boolean isEquipped;

  public void equip() {
    isEquipped = true;
  }

  public void unEquip() {
    isEquipped = false;
  }

  @Value
  public static class InventoryId {
    private final Long value;
  }
}
