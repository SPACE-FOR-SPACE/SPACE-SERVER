package com.space.server.domain.item.domain;

import com.space.server.domain.item.domain.value.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Schema(description = "아이템 Entity")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "아이템 ID")
  private Long id;

  @Column(length = 10)
  @NotNull
  @Schema(description = "아이템 이름")
  private String name;

  @NotNull
  @Schema(description = "아이템 가격")
  private Integer price;

  @Column(name = "item_image", length = 100)
  @NotNull
  @Schema(description = "아이템 이미지 URL")
  private String image;

  @Enumerated(EnumType.STRING)
  @NotNull
  @Schema(description = "아이템 카테고리")
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
