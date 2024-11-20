package com.space.server.domain.chapter.adapter.out.persistence;

import com.space.server.domain.chapter.domain.Chapter;
import org.springframework.stereotype.Component;

@Component
public class ChapterMapper {

  public Chapter mapToChapter(ChapterJpaEntity chapter) {
    return Chapter.builder()
        .id(new Chapter.ChapterId(chapter.getId()))
        .explanation(chapter.getExplanation())
        .mapObject(chapter.getMapObject())
        .mapObjectImage(chapter.getMapObjectImage())
        .build();
  }

  public ChapterJpaEntity mapToChapterJpaEntity(Chapter chapter) {
    return ChapterJpaEntity.builder()
        .id(chapter.getId() == null ? null : chapter.getId().getValue())
        .explanation(chapter.getExplanation())
        .mapObject(chapter.getMapObject())
        .mapObjectImage(chapter.getMapObjectImage())
        .build();
  }
}
