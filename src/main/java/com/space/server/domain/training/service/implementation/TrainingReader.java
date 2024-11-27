package com.space.server.domain.training.service.implementation;

import com.space.server.domain.training.domain.Training;
import com.space.server.domain.training.domain.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TrainingReader {

    private final TrainingRepository trainingRepository;

    public Training findByKey(String key) {
        return trainingRepository.findByKey(key);
    }

    public List<Training> findAllByUserId(Long userId) {
        return trainingRepository.findAllByUserId(userId);
    }

}
