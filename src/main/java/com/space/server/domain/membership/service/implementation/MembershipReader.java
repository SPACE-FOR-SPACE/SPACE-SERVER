package com.space.server.domain.membership.service.implementation;

import com.space.server.domain.membership.domain.Membership;
import com.space.server.domain.membership.domain.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipReader {

    private final MembershipRepository membershipRepository;

    public List<Membership> findAllByTrainingId(Long trainingId) {
        return membershipRepository.findAllByTrainingId(trainingId);
    }
}
