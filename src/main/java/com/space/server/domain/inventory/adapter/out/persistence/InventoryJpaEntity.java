package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.domain.item.adapter.out.persistence.ItemJpaEntity;
import com.space.server.domain.user.domain.Users;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "inventory")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Users user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private ItemJpaEntity item;

  @Column
  private boolean isEquipped;
}
