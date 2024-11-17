package com.space.server.domain.oauth.handler;

import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.domain.oauth.service.dto.CustomOAuth2User;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.repository.UserRepository;
import com.space.server.domain.user.domain.value.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        log.warn("오어스 성공 핸들러" + customUserDetails.toString());

        Long id = customUserDetails.getId();

        Role role = customUserDetails.getAuthorities().stream()
                .findFirst()
                .map(a -> Role.fromValue(a.getAuthority()))
                .orElse(Role.GUEST);

        Users user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        boolean isNewUser = user == null || user.getAge() == null;
        log.warn("오어스 성공 핸들러 중간 체크 : "+isNewUser);

        if (isNewUser) {
            role = Role.GUEST;
            if (user != null) {
                user.updateRole(role);
                userRepository.save(user);
            }
        }


        String accessToken = jwtUtil.createAccessToken(id, role, "social");
        String refreshToken = jwtUtil.createRefreshToken(id, role, "social");

        jwtUtil.addRefreshToken(id, refreshToken);

        log.warn("소셜 로그인 필터 동작");

        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createAccessCookie("access_social", accessToken).toString());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshCookie("refresh_social", refreshToken).toString());

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        if (isNewUser) {
            log.warn("오어스 성공 핸들러 새로운 유저");
            response.sendRedirect("http://localhost:5173/additional-info");
//            response.sendRedirect("http://localhost:3000/additional-info");
        } else {
            log.warn("오어스 성공 핸들러 원래 있던 유저");
            response.sendRedirect("http://localhost:5173");
        }
    }

}
