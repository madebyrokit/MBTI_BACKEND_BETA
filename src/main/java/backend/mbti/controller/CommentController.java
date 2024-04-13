package backend.mbti.controller;


import backend.mbti.service.CommentService;
import backend.mbti.domain.Comment;
import backend.mbti.dto.comment.CreateCommentRequest;
import backend.mbti.dto.comment.LikeCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public void createComment(@RequestBody CreateCommentRequest createCommentRequest, Authentication authentication) {
        commentService.createComment(createCommentRequest, authentication.getName());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> viewCommentList(@PathVariable Long postId) {
        List<Comment> comments = commentService.getComments(postId);
        if (comments != null) {
            return ResponseEntity.ok(comments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest, Authentication authentication) {
        Boolean comments = commentService.updateComment(updateCommentRequest, authentication.getName());
        if (comments != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public void deleteComment(@RequestBody DeleteCommentRequest deleteCommentRequest, Authentication authentication) {
        commentService.deleteComment(deleteCommentRequest, authentication.getName());
    }

    @PostMapping("/like")
    public void toggleLikeComment(LikeCommentRequest likeCommentRequest, Authentication authentication) {
        commentService.toggleLike(likeCommentRequest.getCommentId(), authentication.getName());
    }
}
