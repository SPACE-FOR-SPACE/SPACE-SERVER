package com.space.server.domain.chat.presentation.dto.response;

public record VocabularyFeedbackResponse(
        String vocabularyAnalysis,
        String aiGeneratedFeedback
) {
}
