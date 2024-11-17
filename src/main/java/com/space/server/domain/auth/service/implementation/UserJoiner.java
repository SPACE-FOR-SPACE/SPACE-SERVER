package com.space.server.domain.auth.service.implementation;

import com.space.server.domain.inventory.adapter.out.persistence.EquipInventoryAdapter;
import com.space.server.domain.inventory.application.port.out.CreateInventoryPort;
import com.space.server.domain.auth.presentation.dto.request.JoinUserRequest;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.application.port.in.GetItemQuery;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.mail.exception.EmailNotVerifiedException;
import com.space.server.domain.mail.service.EmailTokenService;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.repository.UserRepository;
import com.space.server.domain.user.domain.value.Role;
import com.space.server.domain.user.exception.UserExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserJoiner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CreateInventoryPort createInventoryPort;
    private final GetItemQuery getItemQuery;
    private final EquipInventoryAdapter equipInventoryAdapter;
    private final EmailTokenService emailTokenService;

    @Transactional
    public void joinProcess(JoinUserRequest joinUserRequest) {

        String email = joinUserRequest.email();
        String password = joinUserRequest.password();

        Boolean isExist = userRepository.existsByEmail(email);

        if (isExist) {
            throw new UserExistedException();
        }

        if (!emailTokenService.isEmailVerified(email)) {
            throw new EmailNotVerifiedException();
        }

        Users user = Users.normalUserBuilder()
                .username(joinUserRequest.username())
                .type("normal")
                .email(email)
                .password(passwordEncoder.encode(password))
                .age(joinUserRequest.age())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        Item head = getItemQuery.getItem(1L);
        Item theme = getItemQuery.getItem(2L);

        Inventory In_head = createInventoryPort.create(head, user);
        Inventory In_theme = createInventoryPort.create(theme, user);

        equipInventoryAdapter.equipInventory(In_head);
        equipInventoryAdapter.equipInventory(In_theme);
    }
}
