package com.backend.document.chat;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Document(value = "chatRoom")
public class ChatRoomDocument {

    @Id
    private String id;
    private int status; // 상태
    private int chatRoomFavorite; // 즐겨찾기
    private int chatRoomReadCnt; // 안읽은메세지수
    private String chatRoomName; // 채팅방이름
    private String leader;  // 방장 uid
    private List<String> members;  // 채팅방 구성원 uid

}
