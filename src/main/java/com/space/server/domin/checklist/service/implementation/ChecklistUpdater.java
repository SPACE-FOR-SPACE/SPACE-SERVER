package com.space.server.domin.checklist.service.implementation;

import com.space.server.domin.checklist.domain.Checklist;
import org.springframework.stereotype.Service;

@Service
public class ChecklistUpdater {

  public void update(Checklist updatableChecklist, Checklist checklist) {
    updatableChecklist.update(checklist);
  }
}
