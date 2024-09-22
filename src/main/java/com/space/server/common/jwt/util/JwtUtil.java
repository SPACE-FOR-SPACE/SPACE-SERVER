package com.space.server.common.jwt.util;

import com.space.server.auth.domain.Refresh;
import com.space.server.auth.domain.repository.RefreshRepository;
import com.space.server.user.domain.value.Role;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final RefreshRepository refreshRepository;
    @Value("${spring.jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${spring.jwt.refresh.expiration}")
    private long refreshTokenExpiration;

    public JwtUtil(@Value("${spring.jwt.secret}") String secret, RefreshRepository refreshRepository) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.refreshRepository = refreshRepository;
    }

    public String getEmail(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public Role getRole(String token) {
        String roleValue = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
        return Role.fromValue(roleValue);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createAccessToken(String email, Role role) {
        return createJwt("access", email, role, accessTokenExpiration);
    }

    public String createRefreshToken(String email, Role role) {
        return createJwt("refresh", email, role, refreshTokenExpiration);
    }

    public Cookie createAccessCookie(String key, String value){
        return createCookie(key, value, (int) accessTokenExpiration);
    }

    public Cookie createRefreshCookie(String key, String value){
        return createCookie(key, value, (int) refreshTokenExpiration);
    }

    public void addRefreshToken(String email, String refreshToken) {

        Date date = new Date(System.currentTimeMillis() + refreshTokenExpiration);

        Refresh refresh = Refresh.builder()
                .email(email)
                .refreshToken(refreshToken)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refresh);
    }

    public String getTokenFromCookies(HttpServletRequest request, String tokenName) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> tokenName.equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private String createJwt(String category, String email, Role role, long expiredMs) {
        return Jwts.builder()
                .claim("category", category)
                .claim("email", email)
                .claim("role", role.getValue())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    private Cookie createCookie(String key, String value, int maxAge) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);

        return cookie;

    }
}
