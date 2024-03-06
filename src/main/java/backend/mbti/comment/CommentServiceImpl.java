package backend.mbti.comment;

import backend.mbti.domain.comment.Comment;
import backend.mbti.domain.comment.LikeComment;
import backend.mbti.dto.comment.CreateCommentRequest;
import backend.mbti.domain.member.Member;
import backend.mbti.domain.post.Post;
import backend.mbti.dto.comment.DeleteCommentRequest;
import backend.mbti.dto.comment.UpdateCommentRequest;
import backend.mbti.exception.AppException;
import backend.mbti.exception.ErrorCode;
import backend.mbti.post.PostRepository;
import backend.mbti.sign.SignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final SignRepository signRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final LikeCommentRepository likeCommentRepository;


    @Override
    public void createComment(CreateCommentRequest createCommentRequest, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, "회원을 찾을 수 없습니다."));

        Post post = postRepository.findById(createCommentRequest.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND, "게시물을 찾을 수 없습니다."));

        Comment comment = new Comment(
                createCommentRequest.getContent(),
                post,
                member,
                createCommentRequest.getOption(),
                new Date());

        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND, "게시물을 찾을 수 없습니다."));

        return commentRepository.findByPost(post);
    }

    @Override
    public void updateComment(UpdateCommentRequest updateCommentRequest, String username) {
        Comment comment = commentRepository.findById(updateCommentRequest.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND, ""));

        comment.setContent(updateCommentRequest.getContent());
        comment.setSelectOption(updateCommentRequest.getOption());
    }

    @Override
    public void deleteComment(DeleteCommentRequest deleteCommentRequest, String username) {
        Comment comment = commentRepository.findById(deleteCommentRequest.getCommentId())
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        commentRepository.delete(comment);
    }

    @Override
    public void toggleLike(Long commentId, String username) {
    }
}