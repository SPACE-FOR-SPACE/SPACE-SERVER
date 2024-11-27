package com.space.server.domain.state.domain.repository;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByQuizAndUser(Quiz quiz, Users user);

    List<State> findByUser(Users users);

    @Query("SELECT s FROM State s " + "JOIN s.quiz q " + "WHERE q.chapter = :chapter AND s.user = :user")
    List<State> findByChapterAndUser(@Param("chapter") Chapter chapter, @Param("user") Users user);

    List<State> findAllByUser(Users user);
}
