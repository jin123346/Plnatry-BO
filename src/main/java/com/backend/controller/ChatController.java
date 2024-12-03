package com.backend.controller;

import com.backend.document.chat.ChatMemberDocument;
import com.backend.document.chat.ChatRoomDocument;
import com.backend.dto.chat.ChatRoomDTO;
import com.backend.entity.user.User;
import com.backend.service.ChatService;
import com.backend.service.GroupService;
import com.backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RequestMapping("/api/message")
@CrossOrigin(origins = "http://localhost:8010")
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    private final GroupService groupService;

    @GetMapping("/room/{userUid}")
    public ResponseEntity<?> getAllChatRooms(@PathVariable String userUid) {
        log.info("userUid = " + userUid);

        List<ChatRoomDTO> chatRoomDTOS = chatService.getAllChatRoomsByUserId(userUid);

        if (chatRoomDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(chatRoomDTOS);
        }
    }

    @Transactional
    @PostMapping("/room")
    public ResponseEntity<?> createRoom(
            @ModelAttribute ChatRoomDTO chatRoomDTO) {

        log.info("chatRoomDTO : " + chatRoomDTO);

        String roomId = chatService.createChatRoom(chatRoomDTO);
        log.info("roomId = " + roomId);

        User user = userService.getUserByuid(chatRoomDTO.getLeader());
        log.info("user = " + user);

        String groupName = groupService.findGroupNameByUser(user);
        log.info("groupName = " + groupName);

        ChatMemberDocument savedDocument = chatService.saveChatMember(roomId, user, groupName);

        if (savedDocument != null) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("error");
        }
    }

    @PatchMapping("/frequent")
    public ResponseEntity<?> updateFrequent(@RequestBody ChatRoomDTO chatRoomDTO) {
        log.info("chatRoomDTO : " + chatRoomDTO);

        ChatRoomDocument savedDocument = chatService.updateChatRoomFavorite(chatRoomDTO);
        if (savedDocument != null) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("error");
        }
    }

}
