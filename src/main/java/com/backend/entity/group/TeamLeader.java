package com.backend.entity.group;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name = "team_leader")
public class TeamLeader {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_leader_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
