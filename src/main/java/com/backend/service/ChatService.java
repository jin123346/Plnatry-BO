package com.backend.service;

import com.backend.dto.chat.ChatRoomDTO;
import com.backend.repository.chat.ChatMembersRepository;
import com.backend.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatMembersRepository chatMembersRepository;
    private final ChatRoomRepository chatRoomRepository;

    public void createRoom(ChatRoomDTO chatRoomDTO) {

        if (chatRoomDTO.getChatRoomName() == null || chatRoomDTO.getChatRoomName().isEmpty()) {

        }


    }

}
