package com.space.server.common.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.server.auth.service.dto.CustomUserDetails;
import com.space.server.common.jwt.dto.LoginRequest;
import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.user.domain.value.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        setUsernameParameter("email");
    }

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
        log.warn("자체 로그인 필터 동작");
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createAccessCookie("access_normal", accessToken).toString());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshCookie("refresh_normal", refreshToken).toString());
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){

        response.setStatus(401);
    }

}
