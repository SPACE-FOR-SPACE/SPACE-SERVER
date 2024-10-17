package com.space.server.core.checklist.service;

import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.checklist.service.implementation.ChecklistCreator;
import com.space.server.core.checklist.service.implementation.ChecklistDeleter;
import com.space.server.core.checklist.service.implementation.ChecklistReader;
import com.space.server.core.checklist.service.implementation.ChecklistUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandChecklistService {

  private final ChecklistCreator checklistCreator;
  private final ChecklistReader checklistReader;
  private final ChecklistUpdater checklistUpdater;
  private final ChecklistDeleter checklistDeleter;

  public void createChecklist(Checklist checklist) {
    checklistCreator.create(checklist);
  }

  public void updateChecklist(Long checklistId, Checklist checklist) {
    Checklist updatablechecklist = checklistReader.readOne(checklistId);
    checklistUpdater.update(updatablechecklist, checklist);
  }

  public void deleteChecklist(Long checklistId) {
    Checklist checklist = checklistReader.readOne(checklistId);
    checklistDeleter.delete(checklist);
  }
}
