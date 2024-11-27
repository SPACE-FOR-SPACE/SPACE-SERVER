package com.space.server.domain.membership.service;

import com.space.server.domain.membership.domain.Membership;
import com.space.server.domain.membership.service.implementation.MembershipReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryMembershipService {

    private final MembershipReader membershipReader;

    public List<Membership> findAllMembershipsByTrainingId(Long trainingId) {
        return membershipReader.findAllByTrainingId(trainingId);
    }
}
