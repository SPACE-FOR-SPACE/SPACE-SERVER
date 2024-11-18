package com.space.server.domain.user.presentation.dto.response;

import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.value.Role;

public record UserResponse(
        Long id,
        String username,
        String type,
        String email,
        Role role,
        Integer age,
        Integer point
) {
    public static UserResponse from(Users user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getType(),
                user.getEmail(),
                user.getRole(),
                user.getAge(),
                user.getPoint());
    }
}
