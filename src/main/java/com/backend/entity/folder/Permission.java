package com.backend.entity.folder;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 권한의 고유 ID

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 권한을 가진 사용자

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file; // 권한이 적용된 파일 (NULL이면 폴더에 적용)

    @Column(nullable = true)
    private String folderId; // MongoDB의 폴더 ID (folder._id)

    @Column(nullable = true)
    private String fileId; // MongoDB의 파일 ID (파일에 대한 권한인 경우)


    // 권한을 비트마스크로 저장
    @Column(nullable = false)
    private int permissions;

    @CreationTimestamp
    private LocalDateTime createdAt; // 권한 생성 날짜 및 시간
}
