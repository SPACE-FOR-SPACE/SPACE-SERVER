package com.space.server.domain.checklist.adapter.out.persistence;

import com.space.server.domain.checklist.domain.Checklist;
import org.springframework.stereotype.Component;

@Component
public class ChecklistMapper {

  public Checklist mapToChecklist(ChecklistJpaEntity checklist) {
    return Checklist.builder()
        .id(new Checklist.ChecklistId(checklist.getId()))
        .quiz(checklist.getQuiz())
        .chapter(checklist.getChapter())
        .content(checklist.getContent())
        .build();
  }
}
