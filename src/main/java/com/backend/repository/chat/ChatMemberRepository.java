package com.backend.repository.chat;

import com.backend.document.chat.ChatMemberDocument;
import com.backend.entity.message.ChatMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMemberRepository extends MongoRepository<ChatMemberDocument, Long> {
}