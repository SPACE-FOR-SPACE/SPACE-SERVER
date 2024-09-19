package com.space.server.core.quiz.presentation;

import com.space.server.core.quiz.presentation.dto.request.QuizRequest;
import com.space.server.core.quiz.presentation.dto.request.UpdateQuizRequest;
import com.space.server.core.quiz.presentation.dto.response.QuizResponse;
import com.space.server.core.quiz.service.CommandQuizService;
import com.space.server.core.quiz.service.QueryQuizService;
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
  public void createQuiz(
      @RequestBody QuizRequest request
  ) {
    commandQuizService.create(request.toEntity());
  }

  @GetMapping("/chapters/{chapter-id}/steps/{step-id}")
  public QuizResponse findOne(
      @PathVariable("chapter-id") Long chapterId,
      @PathVariable("step-id") Long stepId
  ) {
    return QuizResponse.from(queryQuizService.findOne(chapterId, stepId));
  }

  @GetMapping("/chapters/{chapter-id}/steps")
  public List<QuizResponse> findAll(@PathVariable("chapter-id") Long chapterId) {
    return queryQuizService.findAll(chapterId).stream()
        .map(QuizResponse::from)
        .toList();
  }

  @GetMapping("/quizzes/{quiz-id}")
  public QuizResponse readOne(@PathVariable("quiz-id") Long quizId) {
    return QuizResponse.from(queryQuizService.readOne(quizId));
  }

  @GetMapping("/quizzes")
  public List<QuizResponse> readAll() {
    return queryQuizService.readAll().stream()
        .map(QuizResponse::from)
        .toList();
  }

  @PutMapping("/quizzes/{quiz-id}")
  public void updateQuiz(
      @PathVariable("quiz-id") Long quizId,
      @RequestBody UpdateQuizRequest request
      ) {
    commandQuizService.update(quizId, request.toEntity());
  }

  @DeleteMapping("/quizzes/{quiz-id}")
  public void deleteQuiz(@PathVariable("quiz-id") Long quizId) {
    commandQuizService.delete(quizId);
  }
}
