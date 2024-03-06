package backend.mbti.post;

import backend.mbti.domain.post.LikePost;
import backend.mbti.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    Long countAllByPost(Post post);
}
