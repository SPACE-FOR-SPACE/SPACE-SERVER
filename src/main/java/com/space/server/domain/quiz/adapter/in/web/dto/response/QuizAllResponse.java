package com.space.server.domain.quiz.adapter.in.web.dto.response;

import com.space.server.domain.quiz.domain.Quiz;
import io.swagger.v3.oas.annotations.media.Schema;

public record QuizAllResponse(

    @Schema(description = "ID", example = "1", required = true)
    Long id
    ) {
    public static QuizAllResponse from(Quiz quiz) {
        return new QuizAllResponse(quiz.getId().getValue());
    }
}
