package com.space.server.core.equip.domain;

import com.space.server.core.item.domain.Item;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "equip")
@Entity
public class Equip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "character_id")
  private Long Id;

//  @ManyToOne
//  @JoinColumn(name = "user_id")
//  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "head", referencedColumnName = "item_id")
  private Item head;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "theme", referencedColumnName = "item_id")
  private Item theme;

  private int point;

  public Equip(Item head, Item theme, int point) {
    this.head = head;
    this.theme = theme;
    this.point = point;
  }

  public void update(Equip equip) {
    this.head = equip.getHead();
    this.theme = equip.getTheme();
  }
}
