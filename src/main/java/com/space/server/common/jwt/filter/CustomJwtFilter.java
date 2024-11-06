package com.space.server.common.jwt.filter;

import com.space.server.common.jwt.exception.DuplicateLoginException;
import com.space.server.common.jwt.exception.InvalidTokenException;
import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.domain.user.domain.value.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CustomJwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final List<String> excludedPaths;
    private static final String NORMAL_ACCESS_TOKEN = "access_normal";
    private static final String SOCIAL_ACCESS_TOKEN = "access_social";

    public CustomJwtFilter(JwtUtil jwtUtil, List<String> excludedPaths) {
        this.jwtUtil = jwtUtil;
        this.excludedPaths = excludedPaths;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        log.warn("커스텀 JWT 필터 uri : " + requestUri);

        if (requestUri.matches("/login")) {
            String socialToken = jwtUtil.getTokenFromCookies(request, SOCIAL_ACCESS_TOKEN);
            if (socialToken != null) {
                throw new DuplicateLoginException();
            }
            filterChain.doFilter(request, response);
            return;
        }
        if (requestUri.startsWith("/oauth2")) {
            String normalToken = jwtUtil.getTokenFromCookies(request, NORMAL_ACCESS_TOKEN);
            if (normalToken != null) {
                throw new DuplicateLoginException();
            }
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = jwtUtil.getTokenFromCookies(request, NORMAL_ACCESS_TOKEN, SOCIAL_ACCESS_TOKEN);

        if (accessToken == null) {
            log.warn("Access token not found in cookies.");
            filterChain.doFilter(request, response);
            return;
        }

        jwtUtil.isExpired(accessToken);

        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {
            log.warn("Invalid Access token category: {}", category);
            throw new InvalidTokenException();
        }

        Long id = jwtUtil.getId(accessToken);
        Role role = jwtUtil.getRole(accessToken);

        GrantedAuthority authority = new SimpleGrantedAuthority(role.getValue());

        Authentication authToken = new UsernamePasswordAuthenticationToken(id, null, Collections.singletonList(authority));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return excludedPaths.stream()
                .anyMatch(pattern ->
                        new AntPathMatcher().match(pattern, request.getServletPath()));
    }
}
