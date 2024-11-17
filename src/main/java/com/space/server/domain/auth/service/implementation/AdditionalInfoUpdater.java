package com.space.server.domain.auth.service.implementation;

import com.space.server.domain.auth.domain.repository.RefreshRepository;
import com.space.server.domain.auth.presentation.dto.request.AdditionalInfoRequest;
import com.space.server.common.jwt.exception.InvalidTokenException;
import com.space.server.common.jwt.exception.RefreshTokenNotFoundException;
import com.space.server.common.jwt.util.JwtUtil;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.repository.UserRepository;
import com.space.server.domain.user.domain.value.Role;
import com.space.server.domain.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdditionalInfoUpdater {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Transactional
    public void update(HttpServletRequest request, HttpServletResponse response, Long userId, AdditionalInfoRequest additionalInfoRequest) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        String refresh = jwtUtil.getTokenFromCookies(request, "refresh_social");

        if (refresh == null) {
            log.warn("Refresh token not found in cookies");
            throw new RefreshTokenNotFoundException();
        }

        jwtUtil.isExpiredRefresh(refresh);

        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {
            log.warn("Invalid token category: {}", category);
            throw new InvalidTokenException();
        }

        Boolean isExist = refreshRepository.existsByRefreshToken(refresh);

        if (!isExist) {
            log.warn("Refresh token not found in database: {}", refresh);
            throw new RefreshTokenNotFoundException();
        }

        user.updateAdditionalInfo(additionalInfoRequest.age(), additionalInfoRequest.username());
        user.updateRole(Role.USER);

        String loginType = jwtUtil.getLoginType(refresh);
        String accessCookieName = "access_" + loginType;
        String refreshCookieName = "refresh_" + loginType;

        Long id = jwtUtil.getId(refresh);
        Role role = Role.USER;

        String newAccess = jwtUtil.createAccessToken(id, role, loginType);
        String newRefresh = jwtUtil.createRefreshToken(id, role, loginType);

        refreshRepository.deleteByRefreshToken(refresh);
        jwtUtil.addRefreshToken(id, newRefresh);

        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createAccessCookie(accessCookieName, newAccess).toString());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshCookie(refreshCookieName, newRefresh).toString());

    }
}
