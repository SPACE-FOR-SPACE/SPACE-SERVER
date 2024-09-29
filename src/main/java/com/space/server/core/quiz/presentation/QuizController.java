package com.space.server.core.quiz.presentation;

import com.space.server.core.quiz.presentation.dto.request.QuizRequest;
import com.space.server.core.quiz.presentation.dto.request.UpdateQuizRequest;
import com.space.server.core.quiz.presentation.dto.response.QuizResponse;
import com.space.server.core.quiz.service.CommandQuizService;
import com.space.server.core.quiz.service.QueryQuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuizController {

  private final CommandQuizService commandQuizService;
  private final QueryQuizService queryQuizService;

  @PostMapping("/quizzes/quiz")
  @Operation(summary = "퀴즈 생성", description = "퀴즈를 생성합니다.")
  public void createQuiz(@RequestBody QuizRequest request) {
    commandQuizService.create(request);
  }

  @GetMapping("/chapters/{chapter-id}/steps/{step-id}")
  @Operation(summary = "챕터Id 및 스텝Id로 퀴즈 조회", description = "해당 챕터Id와 스텝Id의 퀴즈를 조회합니다.")
  public QuizResponse findOne(
      @Parameter(description = "챕터 ID", required = true) @PathVariable("chapter-id") Long chapterId,
      @Parameter(description = "스텝 ID", required = true) @PathVariable("step-id") Long stepId
  ) {
    return QuizResponse.from(queryQuizService.findOne(chapterId, stepId));
  }

  @GetMapping("/chapters/{chapter-id}/steps")
  @Operation(summary = "챕터 내 모든 퀴즈 조회", description = "해당 챕터의 모든 퀴즈를 조회합니다.")
  public List<QuizResponse> findAll(
      @Parameter(description = "챕터 ID", required = true) @PathVariable("chapter-id") Long chapterId
  ) {
    return queryQuizService.findAll(chapterId).stream()
        .map(QuizResponse::from)
        .toList();
  }

  @GetMapping("/quizzes/{quiz-id}")
  @Operation(summary = "퀴즈Id로 조회", description = "해당 ID의 퀴즈를 조회합니다.")
  public QuizResponse readOne(
      @Parameter(description = "퀴즈 ID", required = true) @PathVariable("quiz-id") Long quizId
  ) {
    return QuizResponse.from(queryQuizService.readOne(quizId));
  }

  @GetMapping("/quizzes")
  @Operation(summary = "모든 퀴즈 조회", description = "전체 퀴즈를 조회합니다.")
  public List<QuizResponse> readAll() {
    return queryQuizService.readAll().stream()
        .map(QuizResponse::from)
        .toList();
  }

  @PutMapping("/quizzes/{quiz-id}")
  @Operation(summary = "퀴즈 수정", description = "해당 ID의 퀴즈를 수정합니다.")
  public void updateQuiz(
      @Parameter(description = "퀴즈 ID", required = true) @PathVariable("quiz-id") Long quizId,
      @RequestBody UpdateQuizRequest request
  ) {
    commandQuizService.update(quizId, request.toEntity());
  }

  @DeleteMapping("/quizzes/{quiz-id}")
  @Operation(summary = "퀴즈 삭제", description = "해당 ID의 퀴즈를 삭제합니다.")
  public void deleteQuiz(
      @Parameter(description = "퀴즈 ID", required = true) @PathVariable("quiz-id") Long quizId
  ) {
    commandQuizService.delete(quizId);
  }
}
