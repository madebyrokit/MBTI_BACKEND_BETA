package backend.mbti.repository;

import backend.mbti.domain.Comment;
import backend.mbti.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    Long countCommentByPost(Post post);
}