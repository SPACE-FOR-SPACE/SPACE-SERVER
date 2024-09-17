package com.space.server.core.checklist.domain.repository;

import com.space.server.core.checklist.domain.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
}
