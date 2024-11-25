package com.backend.entity.folder;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
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

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder; // 권한이 적용된 폴더 (NULL이면 파일에 적용)

    @Column(nullable = false)
    private int canRead;

    @Column(nullable = false)
    private int canWrite;

    @Column(nullable = false)
    private int canDelete;

    @Column(nullable = false)
    private int canShare;

    @CreationTimestamp
    private LocalDateTime createdAt; // 권한 생성 날짜 및 시간
}
