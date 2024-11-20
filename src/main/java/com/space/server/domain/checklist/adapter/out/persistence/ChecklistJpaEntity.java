package com.space.server.domain.checklist.adapter.out.persistence;

import com.space.server.domain.chapter.adapter.out.persistence.ChapterJpaEntity;
import com.space.server.domain.quiz.adapter.out.persistence.QuizJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "checklist")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn
  @ManyToOne(fetch = FetchType.LAZY)
  private QuizJpaEntity quiz;

  @JoinColumn
  @ManyToOne(fetch = FetchType.LAZY)
  private ChapterJpaEntity chapter;

  @Column
  private String content;
}
