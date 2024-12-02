package com.backend.service;

import com.backend.document.chat.ChatMemberDocument;
import com.backend.document.chat.ChatRoomDocument;
import com.backend.dto.chat.ChatRoomDTO;
import com.backend.entity.user.User;
import com.backend.repository.chat.ChatMemberRepository;
import com.backend.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatMemberRepository chatMemberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    public String createChatRoom(ChatRoomDTO chatRoomDTO) {

        ChatRoomDocument chatRoomDocument = chatRoomDTO.toDocument();
        log.info("chatRoomDocument: " + chatRoomDocument);
        ChatRoomDocument savedDocument = chatRoomRepository.save(chatRoomDocument);
        return savedDocument.getId();
    }

    public void getAllChatRoomsByUserId(String userId) {}

    public void saveChatMember(String chatRoomId, User user, String groupName) {
        ChatMemberDocument chatMemberDocument = new ChatMemberDocument();
        chatMemberDocument.setUid(user.getUid());
        chatMemberDocument.setEmail(user.getEmail());
        chatMemberDocument.setHp(user.getHp());
        chatMemberDocument.setLevel(user.getLevel());
        chatMemberDocument.setUsername(user.getName());
        chatMemberDocument.setGroup(groupName);

        ChatMemberDocument savedDocument = chatMemberRepository.save(chatMemberDocument);
    }

}
