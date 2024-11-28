package com.space.server.domain.training.presentation.dto.request;

import com.space.server.domain.training.domain.Training;

import java.util.Random;
import java.util.stream.Collectors;

public record CreateTrainingRequest(
        String title,
        String description
) {
    public Training toEntity(CreateTrainingRequest trainingRequest) {
        return Training.builder()
                .title(title)
                .description(description)
                .key(generateUniqueKey())
                .build();
    }

    private String generateUniqueKey() {
        return new Random()
                .ints(6, 0, 36)
                .mapToObj(i -> i < 10
                        ? String.valueOf(i)
                        : String.valueOf((char)('A' + i - 10))
                )
                .collect(Collectors.joining());
    }
}
