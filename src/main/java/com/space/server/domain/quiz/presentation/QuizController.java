package com.space.server.domain.quiz.presentation;

import com.space.server.domain.quiz.presentation.dto.response.QuizAllResponse;
import com.space.server.domain.quiz.presentation.dto.response.QuizResponse;
import com.space.server.domain.quiz.service.QueryQuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Quiz", description = "퀴즈 API")
public class QuizController {
  private final QueryQuizService queryQuizService;

  @GetMapping("/quizzes/{quiz-id}")
  @Operation(summary = "퀴즈 조회", description = "해당 퀴즈를 조회합니다.")
  public QuizResponse getQuiz(@PathVariable("quiz-id") long quizId) {
    return QuizResponse.from(queryQuizService.readOne(quizId));
  }

  @GetMapping("/chapters/{chapter-id}/quizzes")
  @Operation(summary = "챕터 내 모든 퀴즈 조회", description = "해당 챕터의 모든 퀴즈를 조회합니다.")
  public List<QuizAllResponse> findAll(
      @Parameter(description = "챕터 ID", required = true) @PathVariable("chapter-id") Long chapterId
  ) {
    return queryQuizService.findAll(chapterId).stream()
        .map(QuizAllResponse::from)
        .toList();
  }
}
