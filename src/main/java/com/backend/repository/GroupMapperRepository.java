package com.backend.repository;

import com.backend.entity.group.GroupMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapperRepository extends JpaRepository<GroupMapper, Long> {
}
