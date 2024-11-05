package com.space.server.domain.state.domain.repository;

import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByQuizAndUser(Quiz quiz, Users user);
}
