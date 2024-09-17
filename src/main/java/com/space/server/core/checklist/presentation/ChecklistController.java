package com.space.server.core.checklist.presentation;

import com.space.server.core.checklist.presentation.dto.request.ChecklistRequest;
import com.space.server.core.checklist.service.CommandChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/checklists")
public class ChecklistController {

  private final CommandChecklistService commandChecklistService;

  @PostMapping("/checklist")
  public void createChecklist(@RequestBody ChecklistRequest request) {
    commandChecklistService.createChecklist(request);
  }
}
