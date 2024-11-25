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
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "folder")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long id; // 폴더의 고유 ID

    @ManyToOne
    @JoinColumn(name = "parent_id") // parent 폴더는 parent_id로 구분합니다.
    private Folder parent; // 상위 폴더 (최상위 폴더의 경우 NULL)

    @Column(nullable = false)
    private String name; // 폴더 이름

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner; // 폴더를 소유한 사용자

    @Column(nullable = false, name = "folder_order")
    private Integer order; // 폴더 순서 (같은 부모 폴더 내에서의 정렬)

    @CreationTimestamp
    private LocalDateTime createdAt; // 폴더 생성 날짜 및 시간

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 폴더 수정 날짜 및 시간

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Folder> children = new ArrayList<>(); // 하위 폴더 목록

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>(); // 폴더 내 파일 목록
}
