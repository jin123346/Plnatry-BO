package com.backend.dto.chat;

import com.backend.document.chat.ChatMemberDocument;
import com.backend.dto.response.UserDto;
import com.backend.entity.user.User;
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
    private List<Long> roomIds = new ArrayList<>();

    public ChatMemberDocument toDocument(){
        return ChatMemberDocument.builder()
                .uid(this.uid)
                .username(this.username)
                .email(this.email)
                .hp(this.hp)
                .level(this.level)
                .group(this.group)
                .profileUrl(this.profileUrl)
                .roomIds(this.roomIds)
                .build();
    }
}

