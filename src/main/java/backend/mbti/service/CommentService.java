package backend.mbti.service;

import backend.mbti.domain.comment.Comment;
import backend.mbti.dto.comment.CreateCommentRequest;
import backend.mbti.dto.comment.DeleteCommentRequest;
import backend.mbti.dto.comment.UpdateCommentRequest;

import java.util.List;


public interface CommentService {
    void createComment(CreateCommentRequest request, String username);
    List<Comment> getComments(Long postId);
    void updateComment(UpdateCommentRequest updateCommentRequest, String username);
    void deleteComment(DeleteCommentRequest deleteCommentRequest, String username);
    void toggleLike(Long commentId, String username);
}
