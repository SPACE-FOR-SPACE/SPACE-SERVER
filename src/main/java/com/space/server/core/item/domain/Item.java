package com.space.server.core.item.domain;

import com.space.server.core.item.domain.value.Category;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
@Entity
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long id;

  private String name;

  private int price;

  @Column(name = "item_image")
  private String image;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Builder
  public Item(String name, int price, String image, Category category) {
    this.name = name;
    this.price = price;
    this.image = image;
    this.category = category;
  }
}

