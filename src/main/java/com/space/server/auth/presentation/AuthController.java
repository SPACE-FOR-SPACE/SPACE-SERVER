package com.space.server.auth.presentation;

import com.space.server.auth.presentation.dto.request.JoinUserRequest;
import com.space.server.auth.service.implementation.ReIssuer;
import com.space.server.auth.service.implementation.UserJoiner;
import com.space.server.auth.service.implementation.UserLoginer;
import com.space.server.common.jwt.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserLoginer userLoginer;
    private final UserJoiner userJoiner;
    private final ReIssuer reIssuer;

    @PostMapping("/login")
    public ResponseEntity<?> customLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            userLoginer.login(loginRequest, response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinProcess(@RequestBody JoinUserRequest joinUserRequest) {

        System.out.println(joinUserRequest);
        userJoiner.joinProcess(joinUserRequest);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        return reIssuer.reissue(request, response);
    }

}
