package com.space.server.core.quiz.presentation;

import com.space.server.core.quiz.presentation.dto.response.QuizResponse;
import com.space.server.core.quiz.service.QueryQuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chapters")

public class QuizController {

  private final QueryQuizService queryQuizService;

  @GetMapping("/{chapter-id}/steps")
  @Operation(summary = "챕터 내 모든 퀴즈 조회", description = "해당 챕터의 모든 퀴즈를 조회합니다.")
  public List<QuizResponse> findAll(
      @Parameter(description = "챕터 ID", required = true) @PathVariable("chapter-id") Long chapterId
  ) {
    return queryQuizService.findAll(chapterId).stream()
        .map(QuizResponse::from)
        .toList();
  }

  @GetMapping("/{chapter-id}/steps/{step-id}")
  @Operation(summary = "챕터Id 및 스텝Id로 퀴즈 조회", description = "해당 챕터의 해당 퀴즈를 조회합니다.")
  public QuizResponse findOne(
      @Parameter(description = "챕터 ID", required = true) @PathVariable("chapter-id") Long chapterId,
      @Parameter(description = "스텝 ID", required = true) @PathVariable("step-id") Long stepId
  ) {
    return QuizResponse.from(queryQuizService.findOne(chapterId, stepId));
  }
}
