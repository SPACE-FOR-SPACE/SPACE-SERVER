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
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@Slf4j
public class OAuth2JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final List<String> excludedPaths;

    public OAuth2JwtFilter(JwtUtil jwtUtil, List<String> excludedPaths) {
        this.jwtUtil = jwtUtil;
        this.excludedPaths = excludedPaths;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        log.warn("오어스 JWT 필터 uri" + requestUri);

        if (requestUri.matches("^\\/login(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }
        if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = jwtUtil.getTokenFromCookies(request, "access_social");

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

        log.warn("오어스 JWT 필터 : "+id+role);

        GrantedAuthority authority = new SimpleGrantedAuthority(role.getValue());

        Authentication authToken = new UsernamePasswordAuthenticationToken(id, null, Collections.singletonList(authority));

        log.warn("오어스 JWT 필터 : "+authToken);

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return excludedPaths.stream()
                .anyMatch(pattern ->
                        new AntPathMatcher().match(pattern, request.getServletPath()));
    }

    private void respondWithUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print("{\"error\": \"" + message + "\"}");
        writer.flush();
    }
}
