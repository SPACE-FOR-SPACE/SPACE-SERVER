package com.space.server.common.jwt.filter;

import com.space.server.auth.domain.repository.RefreshRepository;
import com.space.server.common.jwt.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = request.getRequestURI();
        if (!requestUri.matches("^\\/logout$") || !request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshNormal = jwtUtil.getTokenFromCookies(request, "refresh_normal");
        String refreshSocial = jwtUtil.getTokenFromCookies(request, "refresh_social");

        if (refreshNormal != null) {
            processLogout(refreshNormal, "refresh_normal", response);
        } else if (refreshSocial != null) {
            processLogout(refreshSocial, "refresh_social", response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void processLogout(String refreshToken, String cookieName, HttpServletResponse response) {
        try {
            jwtUtil.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String category = jwtUtil.getCategory(refreshToken);
        if (!category.equals("refresh")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Boolean isExist = refreshRepository.existsByRefreshToken(refreshToken);
        if (!isExist) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        refreshRepository.deleteByRefreshToken(refreshToken);

        Cookie refreshCookie = jwtUtil.invalidCookie(cookieName);
        response.addCookie(refreshCookie);

        String accessCookieName = cookieName.replace("refresh", "access");
        Cookie accessCookie = jwtUtil.invalidCookie(accessCookieName);
        response.addCookie(accessCookie);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
