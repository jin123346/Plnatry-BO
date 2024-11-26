package com.backend.repository;

import com.backend.entity.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);

    List<Group> findAllByStatus(int i);

    Long countByStatusAndType(int i, int i1);

    List<Group> findAllByTypeAndStatus(int i, int i1);
}
