package com.backend.entity.user;

import com.backend.dto.response.GetAdminUsersRespDto;
import com.backend.entity.group.Group;
import com.backend.entity.group.GroupMapper;
import com.backend.util.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "level")
    private Integer level;

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

    @Column(name = "payment_token")
    private String paymentToken; // 결제정보

    @Column(name = "payment_day")
    private String day;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToMany(mappedBy = "user")
    private List<GroupMapper> groupMappers;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private ProfileImg profileImg;



    public GetAdminUsersRespDto toGetAdminUsersRespDto() {
        return GetAdminUsersRespDto.builder()
                .email(email)
                .uid(uid)
                .id(id)
                .build();
    }

    public void updateRole(Role role) {
        this.role = role;
    }
}
