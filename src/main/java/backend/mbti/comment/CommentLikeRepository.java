package backend.mbti.comment;

import backend.mbti.domain.comment.Comment;
import backend.mbti.domain.comment.CommentLike;
import backend.mbti.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    CommentLike findByCommentAndMember(Comment comment, Member member);
}
