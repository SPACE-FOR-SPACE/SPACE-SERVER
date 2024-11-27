package com.space.server.domain.membership.domain.repository;

import com.space.server.domain.membership.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findAllByTrainingId(Long trainingId);
}
