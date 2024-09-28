package com.space.server.common.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.server.auth.domain.Refresh;
import com.space.server.auth.domain.repository.RefreshRepository;
import com.space.server.auth.service.dto.CustomUserDetails;
import com.space.server.common.jwt.dto.LoginRequest;
import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.user.domain.value.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            String email = loginRequest.email();
            String password = loginRequest.password();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication){

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Long id = customUserDetails.getId();

        String accessToken = jwtUtil.createAccessToken(id, Role.USER);
        String refreshToken = jwtUtil.createRefreshToken(id, Role.USER);


        jwtUtil.addRefreshToken(id, refreshToken);

        response.addCookie(jwtUtil.createAccessCookie("access_normal", accessToken));
        response.addCookie(jwtUtil.createRefreshCookie("refresh_normal", refreshToken));
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){

        response.setStatus(401);
    }

}
