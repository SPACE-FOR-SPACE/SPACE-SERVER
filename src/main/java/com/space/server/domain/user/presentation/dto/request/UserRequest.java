package com.space.server.domain.user.presentation.dto.request;

import com.space.server.domain.user.domain.Users;

public record UserRequest(
        String username,
        Integer age
) {
    public Users toEntity() {
        return Users.updateUserBuilder()
                .username(username)
                .age(age)
                .build();
    }
}
