package com.backend.dto.request;

import com.backend.entity.folder.FileMogo;
import com.backend.util.Status;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class FileRequestDto {

    private String id; // 파일의 고유 ID
    private String folderId; // 파일이 속한 폴더
    private String originalName;
    private String savedName; // 파일 이름 (사용자가 지정한 이름)
    private String path; // 실제 저장된 파일 경로 (서버 디렉토리 또는 클라우드 URL)
    private Double file_order; // 파일 순서 (같은 폴더 내에서의 정렬)
    private Long size; // 파일 크기 (바이트 단위)
    private String owner_uid; // 파일을 업로드한 사용자
    private Integer version = 1; // 파일 버전 (업데이트 시 증가)
    private int status; // 파일 삭제 여부 (논리적 삭제)
    private int isShared;
    private int isPinned; // 1: 고정 폴더
    private LocalDateTime createdAt; // 파일 생성 날짜 및 시간
    private LocalDateTime updatedAt; // 파일 수정 날짜 및 시간

    private String thumbnailPath;
    public FileMogo toEntity(){
        FileMogo fileMogo = FileMogo.builder()
                .file_order(file_order)
                .isPinned(isPinned)
                .isShared(isShared)
                .originalName(originalName)
                .savedName(savedName)
                .path(path)
                .owner_uid(owner_uid)
                .version(version)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .size(size)
                .folderId(folderId)
                .build();
        return fileMogo;
    }

    public static FileRequestDto toDto(FileMogo fileMogo){
        FileRequestDto fileRequestDto = FileRequestDto.builder()
                .id(fileMogo.getId())
                .folderId(fileMogo.getFolderId())
                .originalName(fileMogo.getOriginalName())
                .savedName(fileMogo.getSavedName())
                .path(fileMogo.getPath())
                .file_order(fileMogo.getFile_order())
                .size(fileMogo.getSize())
                .owner_uid(fileMogo.getOwner_uid())
                .version(fileMogo.getVersion())
                .status(fileMogo.getStatus())
                .createdAt(fileMogo.getCreatedAt())
                .updatedAt(fileMogo.getUpdatedAt())
                .size(fileMogo.getSize())
                .folderId(fileMogo.getFolderId())
                .build();
        return fileRequestDto;
    }
}