package com.backend.dto.chat;

import com.backend.document.chat.ChatMemberDocument;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMembersDTO {

    @Id
    private String uid;
    private String username;
    private String email;
    private String hp;
    private Integer level;
    private String group;
    private String profileUrl;
    private List<ChatMembersDTO> frequent_members;
    private List<String> roomIds = new ArrayList<>();

    public ChatMemberDocument toDocument() {
        return ChatMemberDocument.builder()
                .uid(this.uid)
                .username(this.username)
                .email(this.email)
                .hp(this.hp)
                .level(this.level)
                .group(this.group)
                .profileUrl(this.profileUrl)
                .frequent_members(this.frequent_members.stream().map(ChatMembersDTO::toDocument).toList())
                .roomIds(this.roomIds)
                .build();
    }
}

