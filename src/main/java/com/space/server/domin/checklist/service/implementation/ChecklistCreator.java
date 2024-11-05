package com.space.server.domin.checklist.service.implementation;

import com.space.server.domin.checklist.domain.Checklist;
import com.space.server.domin.checklist.domain.repository.ChecklistRepository;
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
