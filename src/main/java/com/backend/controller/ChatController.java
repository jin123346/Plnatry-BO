package com.backend.controller;

import com.backend.dto.chat.ChatRoomDTO;
import com.backend.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping("/api/message")
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/room")
    public ResponseEntity<?> createRoom(@RequestParam("chatMembers") String chatMembers) {

        log.info("chatMembers : " + chatMembers);

        return ResponseEntity.ok("success");
    }


}
