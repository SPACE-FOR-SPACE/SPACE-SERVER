package com.space.server.auth.service.dto;

import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.value.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Users user;


    private GrantedAuthority getAuthority(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.name());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        switch (user.getRole()) {
            case GUEST:
                authorityList.add(getAuthority(Role.GUEST));
                break;
            case USER:
                authorityList.add(getAuthority(Role.USER));
                break;
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public String getEmail() { return user.getEmail(); }

    public Long getId() { return user.getId(); }
}
