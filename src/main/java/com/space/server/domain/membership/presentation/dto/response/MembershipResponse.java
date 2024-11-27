package com.space.server.domain.membership.presentation.dto.response;

import com.space.server.domain.membership.domain.Membership;
import com.space.server.domain.membership.domain.value.Authority;
import lombok.Builder;

@Builder
public record MembershipResponse(
        Long id,
        Long userId,
        Long trainingId,
        Authority authority
) {
    public static MembershipResponse from(Membership membership) {
        return MembershipResponse.builder()
                .id(membership.getId())
                .userId(membership.getUser().getId())
                .trainingId(membership.getTraining().getId())
                .authority(membership.getAuthority())
                .build();
    }
}
