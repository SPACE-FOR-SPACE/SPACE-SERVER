package com.space.server.core.checklist.service.implementation;

import com.space.server.core.checklist.domain.Checklist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChecklistUpdater {

  public void update(Checklist updatableChecklist, Checklist checklist) {
    updatableChecklist.update(checklist);
  }
}
