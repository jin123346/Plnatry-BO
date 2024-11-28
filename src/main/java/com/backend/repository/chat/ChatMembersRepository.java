package com.backend.repository.chat;

import com.backend.entity.message.ChatMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMembersRepository extends JpaRepository<ChatMembers, Long> {
}
