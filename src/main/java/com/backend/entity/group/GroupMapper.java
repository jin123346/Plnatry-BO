package com.backend.entity.group;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Entity
@Table(name = "group_mapper")
public class GroupMapper {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_mapper_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;  // 그룹

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 사용자

}
