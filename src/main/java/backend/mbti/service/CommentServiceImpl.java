package backend.mbti.service;

import backend.mbti.repository.CommentRepository;
import backend.mbti.repository.LikeCommentRepository;
import backend.mbti.domain.Comment;
import backend.mbti.dto.comment.CreateCommentRequest;
import backend.mbti.domain.Member;
import backend.mbti.domain.Post;
import backend.mbti.configuration.exception.AppException;
import backend.mbti.configuration.exception.ErrorCode;
import backend.mbti.repository.PostRepository;
import backend.mbti.repository.SignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        Post post = postRepository.findById(createCommentRequest.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

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
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        return commentRepository.findByPost(post);
    }

    @Override
    public Boolean updateComment(UpdateCommentRequest updateCommentRequest, String username) {
        Comment comment = commentRepository.findById(updateCommentRequest.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        comment.setContent(updateCommentRequest.getContent());
        comment.setSelectOption(updateCommentRequest.getOption());

        //X
        return true;
    }

    @Override
    public void deleteComment(DeleteCommentRequest deleteCommentRequest, String username) {
        Comment comment = commentRepository.findById(deleteCommentRequest.getCommentId())
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        commentRepository.delete(comment);
    }

    @Override
    public void toggleLike(Long commentId, String username) {
    }
}