package com.space.server.domain.user.service;

import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryUserService {

    private final UserReader userReader;

    public Users readOne(Long userId) {
        return userReader.findById(userId);
    }
}
