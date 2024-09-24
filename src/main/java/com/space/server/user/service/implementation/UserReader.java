package com.space.server.user.service.implementation;

import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReader {

  private final UserRepository userRepository;

  public Users findById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new SpaceException(ErrorCode.USER_NOT_FOUND));
  }
}
