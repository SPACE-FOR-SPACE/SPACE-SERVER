package com.space.server.core.inventory.domain;

import com.space.server.core.item.domain.Item;
import com.space.server.user.domain.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @NotNull
  private Users user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  @NotNull
  private Item item;

  private boolean isEquipped;

  @Builder
  public Inventory(Item item, Users user) {
    this.item = item;
    this.user = user;
  }

  public void equip() {
    isEquipped = true;
  }

  public void unEquip() {
    isEquipped = false;
  }
}
