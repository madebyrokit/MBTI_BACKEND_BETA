package backend.mbti.service;

import backend.mbti.domain.LikeComment;
import backend.mbti.dto.CommentDto;
import backend.mbti.dto.PostDto;
import backend.mbti.repository.CommentRepository;
import backend.mbti.repository.LikeCommentRepository;
import backend.mbti.domain.Comment;
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
    public void createComment(CommentDto.CreateRequest createRequest, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        Post post = postRepository.findById(createRequest.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        Comment comment = new Comment(
                createRequest.getContent(),
                post,
                member,
                createRequest.getOption(),
                new Date()
        );

        LikeComment likeComment = new LikeComment(comment, member);

        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        return commentRepository.findByPost(post);
    }

    @Override
    public Boolean updateComment(CommentDto.UpdateRequest updateCommentRequest, String username) {
        Comment comment = commentRepository.findById(updateCommentRequest.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        comment.setContent(updateCommentRequest.getContent());
        comment.setSelectOption(updateCommentRequest.getOption());


        return true;
    }

    @Override
    public void deleteComment(CommentDto.DeleteRequest deleteCommentRequest, String username) {
        Comment comment = commentRepository.findById(deleteCommentRequest.getCommentId())
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        commentRepository.delete(comment);
    }

    @Override
    public void toggleLike(Long commentId, String username) {
    }
}