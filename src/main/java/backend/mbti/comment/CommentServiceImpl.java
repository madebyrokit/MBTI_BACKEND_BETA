package backend.mbti.comment;

import backend.mbti.domain.comment.Comment;
import backend.mbti.dto.comment.CreateCommentRequest;
import backend.mbti.domain.member.Member;
import backend.mbti.domain.post.Post;
import backend.mbti.dto.comment.UpdateCommentRequest;
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
    public void createComment(Long postId, CreateCommentRequest createCommentRequest, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시물을 찾을 수 없습니다."));

        Comment comment = new Comment(
                createCommentRequest.getContent(),
                0,
                post,
                member,
                createCommentRequest.getOption(),
                new Date());
    }

    @Override
    public List<Comment> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        return commentRepository.findByPost(post);
    }

    @Override
    public void updateComment(Long commentId, UpdateCommentRequest updateCommentRequest, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (!comment.getMember().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not allowed to update this comment");
        }

        comment.setContent(updateCommentRequest.getContent());
    }

    @Override
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (!comment.getMember().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not allowed to delete this comment");
        }

        commentRepository.delete(comment);
    }

    @Override
    public void toggleLike(Long commentId, String username) {
    }
}