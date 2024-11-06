package com.space.server.domain.user.presentation;


import com.space.server.domain.user.presentation.dto.response.UserResponse;
import com.space.server.domain.user.service.CommandUserService;
import com.space.server.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CommandUserService commandUserService;
    private final QueryUserService queryUserService;

    @GetMapping("/users")
    public UserResponse readOne() {
        return UserResponse.from(queryUserService.readOne(getMemberId()));
    }
}
