package com.space.server.domain.membership.presentation;

import com.space.server.domain.membership.domain.Membership;
import com.space.server.domain.membership.presentation.dto.request.CreateMembershipRequest;
import com.space.server.domain.membership.presentation.dto.response.MembershipResponse;
import com.space.server.domain.membership.service.CommandMembershipService;
import com.space.server.domain.membership.service.QueryMembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@Tag(name = "Membership", description = "회원 API")
public class MembershipController {

    private final CommandMembershipService commandMembershipService;
    private final QueryMembershipService queryMembershipService;

    @PostMapping("/memberships")
    @Operation(summary = "클래스룸 참가", description = "해당 클래스룸을 참가합니다.")
    public void create(@RequestBody CreateMembershipRequest request) {

        commandMembershipService.create(getMemberId(), request);

    }


    @GetMapping("/memberships/{trainingId}")
    @Operation(summary = "특정 Training의 Membership 조회", description = "특정 Training에 속한 모든 Membership을 조회합니다.")
    public List<MembershipResponse> findAllMembershipsByTrainingId(@PathVariable Long trainingId) {
        return queryMembershipService.findAllMembershipsByTrainingId(trainingId).stream()
                .map(MembershipResponse::from)
                .toList();
    }

}
