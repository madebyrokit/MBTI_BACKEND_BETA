package backend.mbti.comment;

import backend.mbti.domain.comment.Comment;
import backend.mbti.dto.comment.CommentRequest;
import backend.mbti.dto.comment.UpdateCommentRequest;

import java.util.List;


public interface CommentService {
    List<Comment> getCommentsForPost(Long postId);
    Comment createComment(Long postId, CommentRequest request, String username);
    Comment updateComment(Long commentId, UpdateCommentRequest updateCommentRequest, String username);
    void deleteComment(Long commentId, String username);
    int likePost(Long commentId, String username);
}
