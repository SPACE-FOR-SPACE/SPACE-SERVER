package com.space.server.oauth.service.dto;

import com.space.server.user.domain.value.Role;
import lombok.Builder;

public record UserDto(
        Role role,
        Long id
) {

    @Builder
    public UserDto(Role role, Long id) {
        this.role = role;
        this.id = id;
    }

}
