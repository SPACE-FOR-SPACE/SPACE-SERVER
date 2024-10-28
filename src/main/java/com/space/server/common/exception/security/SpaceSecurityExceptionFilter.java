package com.space.server.common.exception.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.server.common.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class SpaceSecurityExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (SpaceSecurityException e) {
            log.error("[Space Security Exception] URI: {}, ErrorCode: {}, Message: {}",
                    request.getRequestURI(), e.getErrorCode(), e.getMessage());
            handleSpaceSecurityException(response, e);
        } catch (AuthenticationException e) {
            throw e;
        }
    }

    private void handleSpaceSecurityException(HttpServletResponse response, SpaceSecurityException e) throws IOException {
        response.setStatus(e.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ErrorResponse errorResponse = ErrorResponse.from(e.getStatus().value(), e.getErrorCode(), e.getMessage());

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}