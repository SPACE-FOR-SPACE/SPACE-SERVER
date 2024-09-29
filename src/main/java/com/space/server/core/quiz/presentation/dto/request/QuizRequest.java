package com.space.server.core.quiz.presentation.dto.request;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.domain.value.CharacterDirection;

public record QuizRequest(
    Long chapterId,
    Long stepId,
    Long npcId,
    String title,
    String content,
    Integer[][] map,
    CharacterDirection characterDirection
) {}
