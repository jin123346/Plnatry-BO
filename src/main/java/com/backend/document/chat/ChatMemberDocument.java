package com.backend.document.chat;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Document(value = "chatMember")
public class ChatMemberDocument {
    @Id
    private String uid;
    private String username;
    private String email;
    private String hp;
    private Integer level;
    private String group;
    private String profileUrl;
    private List<ChatMemberDocument> frequent_members;
    private List<String> roomIds = new ArrayList<>();
}
