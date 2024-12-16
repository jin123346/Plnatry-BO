package com.backend.dto.chat;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {

    private String type; // 어떤 타입의 요청인지
    private String chatRoomId;
    private Integer unreadCount;
    private String lastMessage; // 해당 채팅방의 마지막 채팅
    private LocalDateTime lastTimeStamp;

}
