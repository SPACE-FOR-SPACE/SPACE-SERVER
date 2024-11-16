package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.domain.item.domain.value.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemJpaEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @Column
  private Integer price;

  @Column
  private String image;

  @Enumerated(EnumType.STRING)
  @Column
  private Category category;
}
