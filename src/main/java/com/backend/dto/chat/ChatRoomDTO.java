package com.backend.dto.chat;

import com.backend.entity.message.ChatRoom;
import com.backend.entity.user.User;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {

    private Long id;

    @Builder.Default
    private int status = 1; // 상태
    @Builder.Default
    private int chatRoomFavorite = 0; // 즐겨찾기
    @Builder.Default
    private int chatRoomReadCnt = 0; // 안읽은메세지수
    private String chatRoomName; // 채팅방이름
    private User leader;  // 방장

    public ChatRoom toEntity(ChatRoomDTO chatRoomDTO) {
        return ChatRoom.builder()
                .id(chatRoomDTO.getId())
                .status(chatRoomDTO.getStatus())
                .chatRoomFavorite(chatRoomDTO.getChatRoomFavorite())
                .chatRoomReadCnt(chatRoomDTO.getChatRoomReadCnt())
                .chatRoomName(chatRoomDTO.getChatRoomName())
                .leader(chatRoomDTO.getLeader())
                .build();
    }

}
