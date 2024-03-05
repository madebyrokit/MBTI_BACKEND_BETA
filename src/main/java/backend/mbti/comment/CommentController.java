package backend.mbti.comment;


import backend.mbti.domain.comment.Comment;
import backend.mbti.dto.comment.CommentRequest;
import backend.mbti.dto.comment.UpdateCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Comment> createComment(@PathVariable Long postId,
                                                 @RequestBody CommentRequest commentRequest,
                                                 Authentication authentication) {
        String username = authentication.getName();
        Comment comment = commentService.createComment(postId, commentRequest, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> viewCommentList(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest, Authentication authentication) {
        String username = authentication.getName();
        Comment updatedComment = commentService.updateComment(commentId, updateCommentRequest, username);

        if (updatedComment != null) {
            return ResponseEntity.ok(updatedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        String username = authentication.getName();
        commentService.deleteComment(commentId, username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<Integer> toggleLikeComment(@PathVariable Long commentId, Authentication authentication) {
        String username = authentication.getName();
        int updatedLikes = commentService.likePost(commentId, username);

        return ResponseEntity.ok().body(updatedLikes);
    }
}
