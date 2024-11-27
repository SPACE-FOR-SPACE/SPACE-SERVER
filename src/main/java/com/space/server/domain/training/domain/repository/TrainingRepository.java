package com.space.server.domain.training.domain.repository;

import com.space.server.domain.training.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    Training findByKey(String key);

    @Query("SELECT t FROM Training t JOIN Membership m ON m.training.id = t.id WHERE m.user.id = :userId")
    List<Training> findAllByUserId(Long userId);

}
