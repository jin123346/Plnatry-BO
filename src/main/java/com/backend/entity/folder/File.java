package com.backend.entity.folder;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 파일의 고유 ID

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder; // 파일이 속한 폴더

    @Column(nullable = false)
    private String name; // 파일 이름 (사용자가 지정한 이름)

    @Column(nullable = false)
    private String path; // 실제 저장된 파일 경로 (서버 디렉토리 또는 클라우드 URL)

    @Column(nullable = false, name = "file_order")
    private Integer order; // 파일 순서 (같은 폴더 내에서의 정렬)


    @Column(nullable = false)
    private Long size; // 파일 크기 (바이트 단위)

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner; // 파일을 업로드한 사용자

    @Column(nullable = false)
    private Integer version = 1; // 파일 버전 (업데이트 시 증가)

    @Column(nullable = false)
    private Boolean isDeleted = false; // 파일 삭제 여부 (논리적 삭제)

    @CreationTimestamp
    private LocalDateTime createdAt; // 파일 생성 날짜 및 시간

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 파일 수정 날짜 및 시간
}