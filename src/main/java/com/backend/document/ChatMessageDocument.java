package com.backend.document;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Document(value="chatMessage")
public class ChatMessageDocument {

    @Id
    private Long id;
    private int status;  // 상태
    private int type; // 공지 ,, 일반채팅 , 파일,
    private String content; // 내용
    private String uid; // 아이디
    private LocalDateTime createdAt; // 보낸 시간
}

