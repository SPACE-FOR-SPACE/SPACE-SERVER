package com.space.server.domain.user.presentation.dto.response;

import com.space.server.domain.user.domain.Users;

public record UserResponse(
        Long userId
) {
    public static UserResponse from(Users users) {
        return new UserResponse(users.getId());
    }
}
