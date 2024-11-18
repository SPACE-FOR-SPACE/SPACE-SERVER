package com.space.server.domain.user.presentation;


import com.space.server.domain.user.presentation.dto.request.UserRequest;
import com.space.server.domain.user.presentation.dto.response.UserResponse;
import com.space.server.domain.user.service.CommandUserService;
import com.space.server.domain.user.service.QueryUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User", description = "유저 API")
public class UserController {

    private final CommandUserService commandUserService;
    private final QueryUserService queryUserService;

    @GetMapping
    @Operation(summary = "유저 조회", description = "유저의 정보를 조회합니다.")
    public UserResponse readOne() {
        return UserResponse.from(queryUserService.readOne(getMemberId()));
    }

    @PutMapping
    @Operation(summary = "유저 수정", description = "유저의 정보를 수정합니다.")
    public void update(@RequestBody UserRequest userRequest) {
        commandUserService.updateUser(getMemberId(), userRequest.toEntity());
    }

    @DeleteMapping
    @Operation(summary = "유저 삭제", description = "유저의 정보를 삭제합니다.")
    public void delete() {
        commandUserService.deleteUser(getMemberId());
    }

}
