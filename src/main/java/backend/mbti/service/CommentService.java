package backend.mbti.service;

import backend.mbti.domain.Comment;
import backend.mbti.dto.comment.CreateCommentRequest;

import java.util.List;


public interface CommentService {
    void createComment(CreateCommentRequest request, String username);
    List<Comment> getComments(Long postId);
    Boolean updateComment(UpdateCommentRequest updateCommentRequest, String username);
    void deleteComment(DeleteCommentRequest deleteCommentRequest, String username);
    void toggleLike(Long commentId, String username);
}
