package com.space.server.domain.membership.service.implementation;

import com.space.server.domain.membership.domain.Membership;
import com.space.server.domain.membership.domain.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipCreator {

    private final MembershipRepository membershipRepository;

    public void create(Membership membership) {
        membershipRepository.save(membership);
    }
}
