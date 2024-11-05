package com.space.server.domin.checklist.service.implementation;

import com.space.server.domin.checklist.domain.Checklist;
import com.space.server.domin.checklist.domain.repository.ChecklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistDeleter {

  private final ChecklistRepository checklistRepository;

  public void delete(Checklist checklist) {
    checklistRepository.delete(checklist);
  }
}
