package backend.mbti.post;



import backend.mbti.domain.post.LikePost;
import backend.mbti.dto.post.PostCreateRequest;
import backend.mbti.dto.post.PostResponse;
import backend.mbti.dto.post.PostUpdateRequest;

import backend.mbti.domain.member.Member;
import backend.mbti.domain.post.Post;
import backend.mbti.exception.AppException;
import backend.mbti.exception.ErrorCode;
import backend.mbti.sign.SignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final SignRepository signRepository;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;

    @Override
    public List<PostResponse> viewPostList() {
        List<Post> postList = postRepository.findAllByOrderByIdDesc();


        return postList.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getOptionA(),
                        post.getOptionB(),
                        post.getViewCountPost().getView(),
                        likePostRepository.countAllByPost(post)))
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse viewPost(Long postId) {
        Optional<Post> post = Optional.ofNullable(postRepository.findById(postId).orElseThrow(
                () -> new AppException(ErrorCode.POST_NOT_FOUND, "게시글을 찾지 못했습니다.")
        ));


        return new PostResponse(
                post.orElseThrow().getId(),
                post.get().getTitle(),
                post.get().getOptionA(),
                post.get().getOptionB(),
                post.get().getViewCountPost().getView(),
                likePostRepository.countAllByPost(post.get()));
    }

    @Override
    public Post createPost(PostCreateRequest request, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("유효하지 않는 사용자"));

        Post post = new Post();
        post.setMember(member);
        post.setTitle(request.getTitle());
        post.setOptionA(request.getOptionA());
        post.setOptionB(request.getOptionB());
        post.setCreatedAt(new Date());
        post.setMember(member);

        return postRepository.save(post);
    }




    // 글 수정
    @Override
    public Post updatePost(Long postId, PostUpdateRequest request, String username) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return null;
        }

        if (!post.getMember().getUsername().equals(username)) {
            throw new AccessDeniedException("게시글 수정 접근 불가");
        }

        post.setTitle(request.getTitle());
        post.setOptionA(request.getOptionA());
        post.setOptionB(request.getOptionB());

        return postRepository.save(post);
    }

    // 글 삭제
    @Override
    public void deletePost(Long postId, String username) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null && post.getMember().getUsername().equals(username)) {
            postRepository.delete(post);
        } else {
            throw new AccessDeniedException("게시글을 찾지 못했습니다.");
        }
    }



    // 댓글 수
    public Integer getCommentCount(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);

return 0;
    }

    // 글 좋아요 (구현 해야함)
    @Override
    @Transactional
    public void likePost(Long postId, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시물을 찾을 수 없습니다."));

    }

}