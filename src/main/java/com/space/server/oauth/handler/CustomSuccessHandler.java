package com.space.server.oauth.handler;

import com.space.server.auth.domain.Refresh;
import com.space.server.auth.domain.repository.RefreshRepository;
import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.oauth.service.dto.CustomOAuth2User;
import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
import com.space.server.user.domain.value.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.warn("오어스 성공 핸들러 authentication : "+authentication);
        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        log.warn("오어스 성공 핸들러" + customUserDetails.toString());

        String email = customUserDetails.getEmail();

        Role role = customUserDetails.getAuthorities().stream()
                .findFirst()
                .map(a -> Role.fromValue(a.getAuthority()))
                .orElse(Role.GUEST);

        Users user = userRepository.findByEmail(email);
        boolean isNewUser = user == null || user.getAge() == null;

        if (isNewUser) {
            role = Role.GUEST;
            if (user != null) {
                user.updateRole(role);
                userRepository.save(user);
            }
        }


        String accessToken = jwtUtil.createAccessToken(email, role);
        String refreshToken = jwtUtil.createRefreshToken(email, role);

        jwtUtil.addRefreshToken(email, refreshToken);

        response.addCookie(jwtUtil.createAccessCookie("access_social", accessToken));
        response.addCookie(jwtUtil.createRefreshCookie("refresh_social", refreshToken));

        if (isNewUser) {
            response.sendRedirect("http://localhost:3000/additional-info");
        } else {
            response.sendRedirect("http://localhost:3000/");
        }
    }

}
