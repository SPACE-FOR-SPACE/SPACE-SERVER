package com.space.server.user.presentation.dto.request;

import com.space.server.user.domain.User;

public record UserSignUpRequest (
        String email,
        String password,
        String nickname,
        Integer age
) {

    public User toEntity(){
        return User.normalUserBuilder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .age(age)
                .build();
    }

}