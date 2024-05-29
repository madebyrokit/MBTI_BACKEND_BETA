package backend.mbti.repository;

import backend.mbti.domain.LikePost;
import backend.mbti.domain.Member;
import backend.mbti.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    Long countByPostId(Long postId);

    LikePost findByMember(Member member);
}
