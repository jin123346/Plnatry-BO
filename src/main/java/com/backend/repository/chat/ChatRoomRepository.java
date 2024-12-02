package com.backend.repository.chat;

import com.backend.document.chat.ChatRoomDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoomDocument, Long> {
}
