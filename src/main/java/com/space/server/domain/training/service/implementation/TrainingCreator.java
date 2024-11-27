package com.space.server.domain.training.service.implementation;

import com.space.server.domain.training.domain.Training;
import com.space.server.domain.training.domain.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingCreator {

    private final TrainingRepository trainingRepository;

    public Training create(Training training) {
        return trainingRepository.save(training);
    }
}
