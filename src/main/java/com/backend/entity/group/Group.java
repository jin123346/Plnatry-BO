package com.backend.entity.group;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name = "user_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name")
    private String name;  // 그룹 이름

    @OneToMany(mappedBy = "group")
    private List<GroupMapper> groupMappers;

    @Column(name = "group_status")
    private int status;  // 부서 관련 상태


    @OneToOne(mappedBy = "group")
    private GroupLeader groupLeader;
}
