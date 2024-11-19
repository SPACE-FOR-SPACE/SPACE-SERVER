package com.space.server.domain.chapter.adapter.out.persistence;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLHStoreType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;

@Builder
@Entity
@Table(name = "chapter")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String explanation;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  private Map<String, String> mapObject;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  private Map<String, String> mapObjectImage;

}
