package com.space.server.common.jwt.filter;

import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.user.domain.value.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        log.warn("자체 JWT 필터 uri : " + requestUri);

        if (requestUri.matches("^\\/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = jwtUtil.getTokenFromCookies(request, "access_normal");

        if (accessToken == null) {
            log.info("Access token not found in cookies.");
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtUtil.isExpired(accessToken)) {
            log.warn("Access token expired");
            respondWithUnauthorized(response, "Access token expired");
            return;
        }

        String category = jwtUtil.getCategory(accessToken);
        if (!category.equals("access")) {
            log.warn("Invalid token category: {}", category);
            respondWithUnauthorized(response, "Invalid access token");
            return;
        }

        Long id = jwtUtil.getId(accessToken);
        Role role = jwtUtil.getRole(accessToken);

        GrantedAuthority authority = new SimpleGrantedAuthority(role.getValue());

        Authentication authToken = new UsernamePasswordAuthenticationToken(id, null, Collections.singletonList(authority));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }


    private void respondWithUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print("{\"error\": \"" + message + "\"}");
        writer.flush();
    }
}
