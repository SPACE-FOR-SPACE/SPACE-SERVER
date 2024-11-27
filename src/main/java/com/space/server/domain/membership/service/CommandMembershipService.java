package com.space.server.domain.membership.service;

import com.space.server.domain.membership.domain.Membership;
import com.space.server.domain.membership.domain.value.Authority;
import com.space.server.domain.membership.presentation.dto.request.CreateMembershipRequest;
import com.space.server.domain.membership.service.implementation.MembershipCreator;
import com.space.server.domain.training.domain.Training;
import com.space.server.domain.training.service.implementation.TrainingReader;
import com.space.server.domain.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandMembershipService {

    private final MembershipCreator membershipCreator;
    private final TrainingReader trainingReader;
    private final UserReader userReader;

    public void create(Long userId, CreateMembershipRequest request) {
        Training training = trainingReader.findByKey(request.key());
        membershipCreator.create(
                Membership.builder()
                        .user(userReader.findById(userId))
                        .training(training)
                        .authority(Authority.MEMBER)
                        .build()
        );
    }
}
