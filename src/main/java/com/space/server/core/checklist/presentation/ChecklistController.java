package com.space.server.core.checklist.presentation;

import com.space.server.core.checklist.presentation.dto.request.ChecklistRequest;
import com.space.server.core.checklist.presentation.dto.response.ChecklistResponse;
import com.space.server.core.checklist.service.CommandChecklistService;
import com.space.server.core.checklist.service.QueryChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/checklists")
public class ChecklistController {

  private final CommandChecklistService commandChecklistService;
  private final QueryChecklistService queryChecklistService;

  @PostMapping("/checklist")
  public void createChecklist(@RequestBody ChecklistRequest request) {
    commandChecklistService.createChecklist(request);
  }

  @GetMapping("/{checklist-id}")
  public ChecklistResponse readOne(@PathVariable(name = "checklist-id") Long checklistId) {
    return ChecklistResponse.from(queryChecklistService.readOne(checklistId));
  }

  @GetMapping
  public List<ChecklistResponse> readAll() {
    return queryChecklistService.readAll().stream()
        .map(ChecklistResponse::from)
        .toList();
  }

  @GetMapping("/quiz/{quiz-id}")
  public List<ChecklistResponse> findByQuiz(@PathVariable(name = "quiz-id") Long quizId) {
    return queryChecklistService.findByQuiz(quizId).stream()
        .map(ChecklistResponse::from)
        .toList();
  }

  @PutMapping("/{checklist-id}")
  public void updateChecklist(
      @PathVariable(name = "checklist-id") Long checklistId,
      @RequestBody ChecklistRequest request
      ) {
    commandChecklistService.updateChecklist(checklistId, request);
  }

  @DeleteMapping("/{checklist-id}")
  public void deleteChecklist(@PathVariable(name = "checklist-id") Long checklistId) {
    commandChecklistService.deleteChecklist(checklistId);
  }
}
