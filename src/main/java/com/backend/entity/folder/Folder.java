package com.backend.entity.folder;

import com.backend.dto.response.drive.FolderDto;
import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Document(collection = "folders")
public class Folder {

    @Id
    private String id; // 폴더의 고유 ID
    private String parentId; // 상위 폴더 ID, 최상위 폴더는 null
    private String path; // 폴더 경로
    private String name; // 폴더 이름
    private String ownerId;
    private Integer order; // 폴더 순서 (같은 부모 폴더 내에서의 정렬)
    private String description; // 폴더 설명
    @CreationTimestamp
    private LocalDateTime createdAt; // 폴더 생성 날짜 및 시간
    @LastModifiedDate
    private LocalDateTime updatedAt; // 폴더 수정 날짜 및 시간
    private List<Folder> children = new ArrayList<>(); // 하위 폴더 목록
    private List<File> files = new ArrayList<>(); // 폴더 내 파일 목록
    @Builder.Default
    private int isShared =0 ; // 공유 여부  0: 나만사용, 2: 선택한 구성원 3: 전체구성원
    @Builder.Default
    private int linkSharing =0 ; //링크 공유 여부  허용하지않음 0 , 허용 1
    @Builder.Default
    private int status = 0; // 상태

    @Builder.Default
    private int isPinned = 0; // 1: 고정 폴더

    public FolderDto toDTO() {
        return FolderDto.builder()
                .id(this.id)
                .parentId(this.parentId)
                .path(this.path)
                .name(this.name)
                .ownerId(this.ownerId)
                .order(this.order)
                .description(this.description)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .isShared(this.isShared)
                .linkSharing(this.linkSharing)
                .status(this.status)
                .isPinned(this.isPinned)
                .build();
    }

    public void newFileName(String newFileName){
        this.name = newFileName;
    }
}
