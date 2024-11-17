package com.space.server.domain.oauth.service.dto;

import com.space.server.domain.user.domain.value.Role;
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
