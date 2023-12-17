package com.management.buildhub.repos;

import com.management.buildhub.models.Objective;
import com.management.buildhub.models.Workplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplanRepository  extends JpaRepository<Workplan, Long> {
}
