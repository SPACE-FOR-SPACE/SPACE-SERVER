package com.space.server.domain.training.service;

import com.space.server.domain.membership.domain.Membership;
import com.space.server.domain.membership.domain.value.Authority;
import com.space.server.domain.membership.service.implementation.MembershipCreator;
import com.space.server.domain.training.domain.Training;
import com.space.server.domain.training.service.implementation.TrainingCreator;
import com.space.server.domain.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandTrainingService {

    private final TrainingCreator trainingCreator;
    private final MembershipCreator membershipCreator;
    private final UserReader userReader;

    public void create(Long userId, Training training) {
        Training createdTraining = trainingCreator.create(training);
        membershipCreator.create(
                Membership.builder()
                        .user(userReader.findById(userId))
                        .training(createdTraining)
                        .authority(Authority.MANAGER)
                .build());
    }




}
