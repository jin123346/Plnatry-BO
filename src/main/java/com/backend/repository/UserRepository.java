package com.backend.repository;

import com.backend.entity.user.User;
import com.backend.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid);

    List<User> findAllByRoleIsNot(Role role);

    List<User> findAllByRole(Role role);
}
