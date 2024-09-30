package com.space.server.chat.domain.repository;

import com.space.server.chat.domain.Chat;
import com.space.server.state.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT COALESCE(MAX(c.order), 0) FROM Chat c WHERE c.state = :state")
    Integer findMaxOrderByState(@Param("state") State state);
}