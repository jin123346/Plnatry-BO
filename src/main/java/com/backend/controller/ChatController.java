package com.backend.controller;

import com.backend.dto.chat.ChatRoomDTO;
import com.backend.entity.user.User;
import com.backend.service.ChatService;
import com.backend.service.GroupService;
import com.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        User user = userService.getUserByuid(userUid);

        return ResponseEntity.ok("success");
    }

    @PostMapping("/room")
    public ResponseEntity<?> createRoom(
            @ModelAttribute ChatRoomDTO chatRoomDTO) {

        log.info("chatRoomDTO : " + chatRoomDTO);

        String roomId = chatService.createChatRoom(chatRoomDTO);

        User user = userService.getUserByuid(chatRoomDTO.getLeader());

        String groupName = groupService.findGroupNameByUser(user);

        chatService.saveChatMember(roomId, user, groupName);

        return ResponseEntity.ok("success");
    }

}
