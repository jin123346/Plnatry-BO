package com.backend.entity.user;

import com.backend.entity.group.DepartmentLeader;
import com.backend.entity.group.Group;
import com.backend.entity.group.TeamLeader;
import com.backend.util.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_status")
    private Integer status; // 상태

    @Column(name = "uid")
    private String uid; // 유아이디

    @Column(name = "pwd")
    private String pwd; // 비밀번호

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role; // 역할

    @Column(name = "grade")
    private Integer grade; // 결제등급 enum 변경

    @Column(name = "email")
    private String email; 

    @Column(name = "hp")
    private String hp;

    @Column(name = "city")
    private String city; 

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "company")
    private String company;

    @ManyToOne(fetch = FetchType.LAZY) // Group과 ManyToOne 관계 설정
    @JoinColumn(name = "group_id") // 외래 키 컬럼 설정
    private Group group;

    @Column(name = "payment_token")
    private String paymentToken; // 결제정보

    @Column(name = "payment_day")
    private String day;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "join_date")
    private LocalDate joinDate;

}
