package com.space.server.auth.service.implementation;

import com.space.server.auth.service.dto.CustomUserDetails;
import com.space.server.common.jwt.dto.LoginRequest;
import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.user.domain.value.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserLoginer {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @Autowired
  public UserLoginer(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  public void login(LoginRequest loginRequest, HttpServletResponse response) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
      );

      CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
      String email = customUserDetails.getEmail();

      String accessToken = jwtUtil.createAccessToken(email, Role.USER);
      String refreshToken = jwtUtil.createRefreshToken(email, Role.USER);

      jwtUtil.addRefreshToken(email, refreshToken);
      response.addCookie(jwtUtil.createAccessCookie("access_normal", accessToken));
      response.addCookie(jwtUtil.createRefreshCookie("refresh_normal", refreshToken));

    } catch (AuthenticationException e) {
      throw new RuntimeException("Authentication failed", e);
    }
  }
}
