package com.backend.dto.community;

import com.backend.entity.community.Comment;
import com.backend.entity.community.Post;
import com.backend.entity.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDTO {

    private Long commentId;

    private Long postId;

    private String content;

    private String writer;

    private LocalDateTime createdAt;

    private Long parentId;

    private String mentionUsername;

    private List<CommentResponseDTO> children;

    private Integer depth;

    private Long likesCount;

    private Boolean isDeleted;

    // Comment 객체를 기반으로 하는 생성자
    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.writer = comment.getUser().getName();

    }

}
