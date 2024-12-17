package com.backend.controller;

import com.backend.dto.community.CommentRequestDTO;
import com.backend.dto.community.CommentResponseDTO;
import com.backend.entity.community.Comment;
import com.backend.entity.community.Post;
import com.backend.entity.user.User;
import com.backend.repository.UserRepository;
import com.backend.repository.community.CommentRepository;
import com.backend.repository.community.PostRepository;
import com.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/community/posts/{postId}")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;


    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDTO>> getComments(@PathVariable Long postId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/comments")
    public ResponseEntity<String> createComment(@RequestBody CommentRequestDTO requestDto) {
        commentService.createComment(requestDto);
        return ResponseEntity.ok("댓글이 성공적으로 등록되었습니다.");
    }


    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDTO requestDto) {
        commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<String> likeComment(@PathVariable Long postIdId, @PathVariable Long commentId) {
        commentService.likeComment(commentId);
        return ResponseEntity.ok("좋아요 상태가 변경되었습니다.");
    }



}
