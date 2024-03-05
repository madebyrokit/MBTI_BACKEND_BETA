package backend.mbti.post;


import backend.mbti.domain.member.Member;
import backend.mbti.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
    List<Post> findByMemberOrderByCreatedAtDesc(Member member);
    Optional<Post> findById(Long postid);
}