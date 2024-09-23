package com.space.server.oauth.service.dto;

import com.space.server.user.domain.value.Role;
import lombok.Builder;

public record UserDto(
        Role role,
        String email
) {

    @Builder
    public UserDto(Role role, String email) {
        this.role = role;
        this.email = email;
    }

}
