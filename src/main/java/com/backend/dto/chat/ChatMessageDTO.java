package com.backend.dto.chat;

import com.backend.document.chat.ChatMemberDocument;
import com.backend.document.chat.ChatMessageDocument;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

    @Id
    private String id;
    private Long roomId; // 채팅방 아이디
    private int status;  // 상태
    private int type; // 공지 ,, 일반채팅 , 파일,
    private String content; // 내용
    private String fileUrl; // 파일 Url
    private String sender; // 보낸 사람 아이디
    private LocalDateTime createdAt; // 보낸 시간

    public ChatMessageDocument toDocument() {
        return ChatMessageDocument.builder()
                .id(this.id)
                .roomId(this.roomId)
                .status(this.status)
                .type(this.type)
                .content(this.content)
                .fileUrl(this.fileUrl)
                .sender(this.sender)
                .createdAt(this.createdAt)
                .build();
    }
}

