package com.space.server.core.item.domain;

import com.space.server.core.item.domain.value.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 10)
  @NotNull
  private String name;

  @NotNull
  private int price;

  @Column(name = "item_image", length = 100)
  @NotNull
  private String image;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Category category;

  @Builder
  public Item(String name, int price, String image, Category category) {
    this.name = name;
    this.price = price;
    this.image = image;
    this.category = category;
  }

  public void update(Item item) {
    this.name = item.getName();
    this.price = item.getPrice();
    this.image = item.getImage();
    this.category = item.getCategory();
  }
}

