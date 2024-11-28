package com.space.server.domain.training.service;


import com.space.server.domain.membership.domain.Membership;
import com.space.server.domain.training.domain.Training;
import com.space.server.domain.training.service.implementation.TrainingReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryTrainingService {

    private final TrainingReader trainingReader;

    public List<Training> findAllTrainingsByUserId(Long userId) {
        return trainingReader.findAllByUserId(userId);
    }


}
