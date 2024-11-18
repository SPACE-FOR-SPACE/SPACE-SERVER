package com.space.server.domain.checklist.adapter.in.web;

import com.space.server.domain.checklist.adapter.in.web.dto.response.ChecklistResponse;
import com.space.server.domain.checklist.application.port.in.GetChecklistsByQuizQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checklists")
@Tag(name = "Checklist", description = "체크리스트 API")
public class ChecklistController {

  private final GetChecklistsByQuizQuery getChecklistsByQuizQuery;


  @GetMapping("/quiz/{quiz-id}")
  @Operation(summary = "퀴즈 내 모든 체크리스트 조회", description = "해당 퀴즈의 전체 체크리스트를 조회합니다.")
  public List<ChecklistResponse> findByQuiz(@PathVariable(name = "quiz-id") @Parameter(description = "퀴즈 ID") Long quizId) {
    return getChecklistsByQuizQuery.getChecklistsByQuiz(quizId).stream()
        .map(ChecklistResponse::from)
        .toList();
  }
}