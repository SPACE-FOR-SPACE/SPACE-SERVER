package com.space.server.common.infra.filter;


import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class SwaggerApiPrefixFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Swagger 요청인지 확인
        String path = httpRequest.getRequestURI();
        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")) {
            // `/api` 접두사가 없으면 추가
            String modifiedPath = path.startsWith("/api") ? path : "/api" + path;

            // 리매핑 수행
            httpRequest.getRequestDispatcher(modifiedPath).forward(httpRequest, httpResponse);
            return;
        }

        // Swagger 요청이 아닌 경우 그대로 진행
        chain.doFilter(request, response);
    }
}
