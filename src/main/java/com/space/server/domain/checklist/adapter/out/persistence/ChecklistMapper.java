package com.space.server.domain.checklist.adapter.out.persistence;

import com.space.server.domain.chapter.adapter.out.persistence.ChapterMapper;
import com.space.server.domain.checklist.domain.Checklist;
import com.space.server.domain.item.adapter.out.persistence.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChecklistMapper {

  private final ChapterMapper chapterMapper;

  public Checklist mapToChecklist(ChecklistJpaEntity checklist) {
    return Checklist.builder()
        .id(new Checklist.ChecklistId(checklist.getId()))
        .quiz(checklist.getQuiz())
        .chapter(chapterMapper.mapToChapter(checklist.getChapter()))
        .content(checklist.getContent())
        .build();
  }
}
