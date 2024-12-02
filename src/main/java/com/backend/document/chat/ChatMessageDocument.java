package com.backend.document.chat;

import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value="chatMessage")
public class ChatMessageDocument {

    @Id
    private String id;
    private Long roomId; // 채팅방 아이디
    private int status;  // 상태
    private int type; // 공지 ,, 일반채팅 , 파일,
    private String content; // 내용
    private String fileUrl; // 파일 Url
    private String sender; // 보낸 사람 아이디
    @CreationTimestamp
    private LocalDateTime createdAt; // 보낸 시간
}

