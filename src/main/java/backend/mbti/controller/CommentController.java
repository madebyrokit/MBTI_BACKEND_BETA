package backend.mbti.controller;

import backend.mbti.dto.CommentDto;
import backend.mbti.service.CommentService;
import backend.mbti.domain.Comment;
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
    public void createComment(@RequestBody CommentDto.CreateRequest createRequest, Authentication authentication) {
        commentService.createComment(createRequest, authentication.getName());
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
    public ResponseEntity<?> updateComment(@RequestBody CommentDto.UpdateRequest updateRequest, Authentication authentication) {
        Boolean comments = commentService.updateComment(updateRequest, authentication.getName());
        if (comments != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public void deleteComment(@RequestBody CommentDto.DeleteRequest deleteRequest, Authentication authentication) {
        commentService.deleteComment(deleteRequest, authentication.getName());
    }

    @PostMapping("/like")
    public void toggleLikeComment(CommentDto.LikeRequest likeRequest, Authentication authentication) {
        commentService.toggleLike(likeRequest.getCommentId(), authentication.getName());
    }
}
