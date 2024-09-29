package com.space.server.auth.service.implementation;

import com.space.server.auth.domain.repository.RefreshRepository;
import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.user.domain.value.Role;
import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReIssuer {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refresh = jwtUtil.getTokenFromCookies(request, "refresh_normal");

        if (refresh == null) {
            log.warn("Refresh token not found in cookies");
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            log.warn("Refresh token expired: {}", e.getMessage());
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }


        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {
            log.warn("Invlid token category: {}", category);
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        Boolean isExist = refreshRepository.existsByRefreshToken(refresh);

        if (!isExist) {
            log.warn("Refresh token not found in database: {}", refresh);
            return new ResponseEntity<>("refresh token not found", HttpStatus.BAD_REQUEST);
        }

        Long id = jwtUtil.getId(refresh);
        Role role = jwtUtil.getRole(refresh);


        String newAccess = jwtUtil.createAccessToken(id, role);
        String newRefresh = jwtUtil.createRefreshToken(id, role);

        refreshRepository.deleteByRefreshToken(refresh);
        jwtUtil.addRefreshToken(id, newRefresh);

        response.addCookie(jwtUtil.createAccessCookie("access_normal", newAccess));
        response.addCookie(jwtUtil.createRefreshCookie("refresh_normal", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
