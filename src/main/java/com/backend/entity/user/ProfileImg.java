package com.backend.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProfileImg {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long profileImgId;

    @Column(name = "profile_status")
    private int status; // 상태

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user; // 프로필도

    @Column(name = "r_name")
    private String rName;

    @Column(name = "s_name")
    private String sName; // uuid 바뀐 파일이름

    @Column(name = "message")
    private String message; // 소개말
}
