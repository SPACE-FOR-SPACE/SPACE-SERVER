package com.space.server.auth.service.implementation;

import com.space.server.auth.presentation.dto.request.JoinUserRequest;
import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
import com.space.server.user.domain.value.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJoiner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public void joinProcess(JoinUserRequest joinUserRequest) {

        String email = joinUserRequest.email();
        String password = joinUserRequest.password();

        Boolean isExist = userRepository.existsByEmail(email);

        if (isExist) {

            return;
        }

        Users user = Users.normalUserBuilder()
                .username(joinUserRequest.username())
                .type("NORMAL")
                .email(email)
                .password(passwordEncoder.encode(password))
                .age(joinUserRequest.age())
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }
}
