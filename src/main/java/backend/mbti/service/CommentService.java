package backend.mbti.service;

import backend.mbti.domain.Comment;
import backend.mbti.dto.CommentDto;

import java.util.List;


public interface CommentService {
    void createComment(CommentDto.CreateRequest create, String username);
    List<Comment> getComments(Long postId);
    Boolean updateComment(CommentDto.UpdateRequest updateCommentRequest, String username);
    void deleteComment(CommentDto.DeleteRequest delete, String username);
    void toggleLike(Long commentId, String username);
}
