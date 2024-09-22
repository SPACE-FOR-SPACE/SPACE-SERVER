package com.space.server.user.domain.repository;

import com.space.server.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Boolean existsByEmail(String email);

    Users findByEmail(String email);

    Users findByType(String type);
}
