package com.space.server.domain.chat.domain.repository;

import com.space.server.domain.chat.domain.Chat;
import com.space.server.domain.state.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT COALESCE(MAX(c.request_order), 0) FROM Chat c WHERE c.state = :state")
    Integer findMaxOrderByState(@Param("state") State state);

    List<Chat> findAllChatByState(@Param("stateId") State state);

    void deleteByState(State state);
}
