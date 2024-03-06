package backend.mbti.comment;

import backend.mbti.domain.comment.Comment;
import backend.mbti.dto.comment.CreateCommentRequest;
import backend.mbti.dto.comment.UpdateCommentRequest;

import java.util.List;


public interface CommentService {
    void createComment(Long postId, CreateCommentRequest request, String username);
    List<Comment> getComments(Long postId);
    void updateComment(Long commentId, UpdateCommentRequest updateCommentRequest, String username);
    void deleteComment(Long commentId, String username);
    void toggleLike(Long commentId, String username);
}
