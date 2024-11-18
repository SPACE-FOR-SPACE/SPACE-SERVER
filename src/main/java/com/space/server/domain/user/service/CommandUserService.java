package com.space.server.domain.user.service;

import com.space.server.domain.inventory.service.implementation.InventoryDeleter;
import com.space.server.domain.state.service.implementation.StateDeleter;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.service.implementation.UserDeleter;
import com.space.server.domain.user.service.implementation.UserReader;
import com.space.server.domain.user.service.implementation.UserUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandUserService {

    private final UserUpdater  userUpdater;
    private final UserReader userReader;
    private final UserDeleter userDeleter;
    private final StateDeleter stateDeleter;
    private final InventoryDeleter inventoryDeleter;

    public void updateUser(Long userId, Users user) {
        Users updatableUser = userReader.findById(userId);

        userUpdater.update(updatableUser, user);
    }

    public void deleteUser(Long userId) {
        Users deletableUser = userReader.findById(userId);

        inventoryDeleter.deleteByUser(deletableUser);
        stateDeleter.deleteByUser(deletableUser);
        userDeleter.delete(deletableUser);
    }
}
