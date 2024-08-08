package com.space.server.core.inventory.domain;

import com.space.server.core.item.domain.Item;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inventory")
@Entity
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

//  @ManyToOne
//  @JoinColumn(name = "user_id")
//  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "head", referencedColumnName = "id")
  private Item head;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "theme", referencedColumnName = "id")
  private Item theme;

  private int point;

  public Inventory(Item head, Item theme, int point) {
    this.head = head;
    this.theme = theme;
    this.point = point;
  }

  public void update(Inventory inventory) {
    this.head = inventory.getHead();
    this.theme = inventory.getTheme();
    this.point = inventory.getPoint();
  }
}
