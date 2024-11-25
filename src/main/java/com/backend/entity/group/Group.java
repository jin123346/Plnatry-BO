package com.backend.entity.group;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "group_type", discriminatorType = DiscriminatorType.STRING)
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

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)  // User와의 관계
    private List<User> users; // 그룹에 속한 사용자들
}
