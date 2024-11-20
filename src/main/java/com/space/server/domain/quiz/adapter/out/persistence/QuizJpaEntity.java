package com.space.server.domain.quiz.adapter.out.persistence;

import com.space.server.domain.chapter.adapter.out.persistence.ChapterJpaEntity;
import com.space.server.domain.quiz.domain.value.CharacterDirection;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLHStoreType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Map;

@Builder
@Entity
@Table(name = "quiz")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn
  @ManyToOne(fetch = FetchType.LAZY)
  private ChapterJpaEntity chapter;

  @Column
  private Long stepId;

  @Column
  private String title;

  @Column
  private String content;


  @Type(IntArrayType.class)
  @Column(columnDefinition = "int[][]")
  private Integer[][] map;

  @Column
  @Enumerated(EnumType.STRING)
  private CharacterDirection characterDirection;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  private Map<String, String> mapObject;

  @Type(PostgreSQLHStoreType.class)
  @Column(columnDefinition = "hstore")
  private Map<String, String> mapObjectImage;
}
