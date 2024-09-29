package com.space.server.core.inventory.domain;

import com.space.server.core.item.domain.Item;
import com.space.server.user.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Schema(description = "인벤토리 Entity")
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "인벤토리 ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @NotNull
  @Schema(description = "사용자 정보")
  private Users user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  @NotNull
  @Schema(description = "아이템 정보")
  private Item item;

  @Schema(description = "아이템 장착 여부", example = "false")
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
