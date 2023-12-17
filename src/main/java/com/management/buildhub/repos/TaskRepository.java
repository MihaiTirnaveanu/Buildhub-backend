package com.management.buildhub.repos;

import com.management.buildhub.models.Objective;
import com.management.buildhub.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
}
