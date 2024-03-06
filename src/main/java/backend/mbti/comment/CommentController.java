package backend.mbti.comment;


import backend.mbti.domain.comment.Comment;
import backend.mbti.dto.comment.CreateCommentRequest;
import backend.mbti.dto.comment.DeleteCommentRequest;
import backend.mbti.dto.comment.LikeCommentRequest;
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

    @PostMapping
    public void createComment(@RequestBody CreateCommentRequest createCommentRequest, Authentication authentication) {
        commentService.createComment(createCommentRequest, authentication.getName());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> viewCommentList(@PathVariable Long postId) {
        List<Comment> comments = commentService.getComments(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping
    public void updateComment(@RequestBody UpdateCommentRequest updateCommentRequest, Authentication authentication) {
        commentService.updateComment(updateCommentRequest, authentication.getName());
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
