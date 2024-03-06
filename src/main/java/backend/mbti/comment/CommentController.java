package backend.mbti.comment;


import backend.mbti.domain.comment.Comment;
import backend.mbti.dto.comment.CreateCommentRequest;
import backend.mbti.dto.comment.UpdateCommentRequest;
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

    @PostMapping("/{postId}")
    public void createComment(@PathVariable Long postId, @RequestBody CreateCommentRequest createCommentRequest, Authentication authentication) {
        commentService.createComment(postId, createCommentRequest, authentication.getName());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> viewCommentList(@PathVariable Long postId) {
        List<Comment> comments = commentService.getComments(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest, Authentication authentication) {
        commentService.updateComment(commentId, updateCommentRequest, authentication.getName());
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId, Authentication authentication) {
        commentService.deleteComment(commentId, authentication.getName());
    }

    @PostMapping("/{commentId}/like")
    public void toggleLikeComment(@PathVariable Long commentId, Authentication authentication) {
        commentService.toggleLike(commentId, authentication.getName());
    }
}
