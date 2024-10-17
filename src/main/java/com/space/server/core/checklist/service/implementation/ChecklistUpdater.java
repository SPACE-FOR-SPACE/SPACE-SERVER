package com.space.server.core.checklist.service.implementation;

import com.space.server.core.checklist.domain.Checklist;
import org.springframework.stereotype.Service;

@Service
public class ChecklistUpdater {

  public void update(Checklist updatableChecklist, Checklist checklist) {
    updatableChecklist.update(checklist);
  }
}
