package com.backend.entity.group;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "department_leader")
public class DepartmentLeader {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_leader_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
