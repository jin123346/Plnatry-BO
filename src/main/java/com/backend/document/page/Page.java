package com.backend.document.page;

import com.backend.dto.request.page.PageDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/*
    날짜 : 2024.12.05
    이름 : 하진희
    내용 : Page 저장을 위한 MogoDB collection
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Document(collection = "pages")
public class Page {
    @Id
    private String id;
    private String title;
    private String content; // content를 Object로 설정
    private String ownerUid;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public void putData(PageDto dto) {
        if(dto.getTitle() != null) {
            this.title = dto.getTitle();
        }
        if(dto.getContent() != null){
            this.content = dto.getContent();
        }
    }
}
