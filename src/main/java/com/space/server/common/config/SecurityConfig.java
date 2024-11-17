package com.space.server.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.server.domain.auth.domain.repository.RefreshRepository;
import com.space.server.common.exception.security.SpaceSecurityExceptionFilter;
import com.space.server.common.jwt.exception.CustomAccessDeniedException;
import com.space.server.common.jwt.exception.UnauthenticatedAccessException;
import com.space.server.common.jwt.filter.CustomLogoutFilter;
import com.space.server.common.jwt.filter.CustomJwtFilter;
import com.space.server.common.jwt.filter.LoginFilter;
import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.domain.oauth.handler.CustomSuccessHandler;
import com.space.server.domain.oauth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    private final ObjectMapper objectMapper;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;

    private final List<String> excludedPaths = Arrays.asList("/swagger-ui/**", "/v3/api-docs/**", "/reissue", "/hc", "/env", "/verify-request", "/verify", "/favicon.ico");

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors((cors) -> cors
                        .configurationSource(request -> {

                            CorsConfiguration configuration = new CorsConfiguration();
//                            configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
                            configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
                            configuration.setAllowedMethods(Collections.singletonList("*"));
                            configuration.setAllowCredentials(true);
                            configuration.setAllowedHeaders(Collections.singletonList("*"));
                            configuration.setMaxAge(3600L);
                            configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization"));


                            return configuration;

                        })
                );

        http
                .csrf((auth) -> auth.disable());

        http
                .formLogin((auth) -> auth.disable());

        http
                .httpBasic((auth) -> auth.disable());

        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler));

        http
                .logout((auth) -> auth.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login","/join", "/reissue","/swagger-ui/**", "/v3/api-docs/**", "/hc", "/env", "/verify-request", "/verify", "/favicon.ico").permitAll()
                        .requestMatchers("/users").hasRole("GUEST")
                        .anyRequest().hasRole("USER"));

        http
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(((request, response, e) -> {
                            throw new UnauthenticatedAccessException();
                        }))
                        .accessDeniedHandler((request, response, e) -> {
                            throw new CustomAccessDeniedException();
                        })
                );

        http
                .addFilterAfter(new SpaceSecurityExceptionFilter(objectMapper), CorsFilter.class);

        http
                .addFilterAfter(new CustomJwtFilter(jwtUtil, excludedPaths), CorsFilter.class);

        http
                .addFilterBefore(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, objectMapper), UsernamePasswordAuthenticationFilter.class);

        http
                .addFilterAt(new CustomLogoutFilter(jwtUtil, refreshRepository), LogoutFilter.class);

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();
    }

}
