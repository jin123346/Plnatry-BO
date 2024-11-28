package com.backend.dto.response;

import com.backend.entity.group.GroupMapper;
import com.backend.entity.user.Attendance;
import com.backend.entity.user.ProfileImg;
import com.backend.util.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class UserDto {

    private Long id;

    private Integer status; // 상태

    private String uid; // 유아이디

    private String pwd; // 비밀번호

    private Role role; // 역할

    private Integer level;

    private Integer grade; // 결제등급 enum 변경

    private String email;

    private String hp;

    private String name;

    private String city;

    private String country;

    private String address;

    private String company;

    private String paymentToken; // 결제정보

    private String day;

    private String refreshToken;

    @ToString.Exclude
    private List<GroupMapper> groupMappers;

    private LocalDateTime createAt;

    private LocalDateTime lastLogin;

    private LocalDate joinDate;

    private String profileImg;

    @ToString.Exclude
    private List<Attendance> attendance;



}
