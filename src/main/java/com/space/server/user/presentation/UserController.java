package com.space.server.user.presentation;

import com.space.server.user.presentation.dto.request.UserSignUpRequest;
import com.space.server.user.service.CommandUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CommandUserService commandUserService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpRequest userSignUpRequest) throws Exception {
        commandUserService.signUp(userSignUpRequest.toEntity());
        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
