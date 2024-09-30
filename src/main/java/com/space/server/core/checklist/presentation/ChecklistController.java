package com.space.server.core.checklist.presentation;

import com.space.server.core.checklist.presentation.dto.request.ChecklistRequest;
import com.space.server.core.checklist.presentation.dto.response.ChecklistResponse;
import com.space.server.core.checklist.service.CommandChecklistService;
import com.space.server.core.checklist.service.QueryChecklistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  @Operation(summary = "체크리스트 생성", description = "체크리스트를 생성합니다.")
  public void createChecklist(@RequestBody ChecklistRequest request) {
    commandChecklistService.createChecklist(request);
  }

  @GetMapping("/{checklist-id}")
  @Operation(summary = "체크리스트 조회", description = "해당 체크리스트를 조회합니다.")
  public ChecklistResponse readOne(@PathVariable(name = "checklist-id") @Parameter(description = "체크리스트 ID") Long checklistId) {
    return ChecklistResponse.from(queryChecklistService.readOne(checklistId));
  }

  @GetMapping
  @Operation(summary = "모든 체크리스트 조회", description = "모든 체크리스트를 조회합니다.")
  public List<ChecklistResponse> readAll() {
    return queryChecklistService.readAll().stream()
        .map(ChecklistResponse::from)
        .toList();
  }

  @GetMapping("/quiz/{quiz-id}")
  @Operation(summary = "퀴즈 내 모든 체크리스트 조회", description = "해당 퀴즈의 전체 체크리스트를 조회합니다.")
  public List<ChecklistResponse> findByQuiz(@PathVariable(name = "quiz-id") @Parameter(description = "퀴즈 ID") Long quizId) {
    return queryChecklistService.findByQuiz(quizId).stream()
        .map(ChecklistResponse::from)
        .toList();
  }

  @PutMapping("/{checklist-id}")
  @Operation(summary = "체크리스트 업데이트", description = "해당 체크리스트를 업데이트합니다.")
  public void updateChecklist(
      @PathVariable(name = "checklist-id") @Parameter(description = "체크리스트 ID") Long checklistId,
      @RequestBody ChecklistRequest request
  ) {
    commandChecklistService.updateChecklist(checklistId, request);
  }

  @DeleteMapping("/{checklist-id}")
  @Operation(summary = "체크리스트 삭제", description = "헤당 체크리스트를 삭제합니다.")
  public void deleteChecklist(@PathVariable(name = "checklist-id") @Parameter(description = "체크리스트 ID") Long checklistId) {
    commandChecklistService.deleteChecklist(checklistId);
  }
}
