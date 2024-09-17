package com.space.server.core.checklist.service.implementation;

import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.checklist.domain.repository.ChecklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistCreator {

  private final ChecklistRepository checklistRepository;

  public void create(Checklist checklist) {
    checklistRepository.save(checklist);
  }
}
