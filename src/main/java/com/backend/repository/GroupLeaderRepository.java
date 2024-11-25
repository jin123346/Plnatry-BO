package com.backend.repository;

import com.backend.entity.group.GroupLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupLeaderRepository extends JpaRepository<GroupLeader, Long> {
}
