package com.space.server.core.quiz.presentation.dto.response;

import com.space.server.core.quiz.domain.Quiz;
import io.swagger.v3.oas.annotations.media.Schema;

public record QuizAllResponse(

    @Schema(description = "ID", example = "1", required = true)
    Long id
    ) {
    public static QuizAllResponse from(Quiz quiz) {
        return new QuizAllResponse(quiz.getId());
    }
}
