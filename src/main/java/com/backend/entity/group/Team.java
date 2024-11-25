package com.backend.entity.group;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("TEAM")  // group_type이 "TEAM"인 경우
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Team extends Group {
    @Column(name = "team_status")
    private int status;  // 팀 관련 상태

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<User> users = new ArrayList<>();

    @Column(name = "team_name")
    private String name;

    @OneToOne(mappedBy = "team")
    private TeamLeader teamLeader;
}