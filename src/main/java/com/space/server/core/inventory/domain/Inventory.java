package com.space.server.core.inventory.domain;

import com.space.server.core.item.domain.Item;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @OneToOne
//  @JoinColumn(name = "user_id")
//  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  private boolean isEquipped;

  @Builder
  public Inventory(Item item) {
    this.item = item;
    this.isEquipped = false;
  }

  public void update(Inventory inventory) {
    this.item = inventory.getItem();
    this.isEquipped = inventory.isEquipped();
  }
}
