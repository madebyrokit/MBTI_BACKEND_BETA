package backend.mbti.post;

import backend.mbti.domain.post.LikePost;
import backend.mbti.domain.post.ViewCountPost;
import backend.mbti.dto.post.CreatePostRequest;
import backend.mbti.dto.post.PostResponse;
import backend.mbti.dto.post.UpdatePostRequest;

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
    public void createPost(CreatePostRequest createPostRequest, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, "USERNAME_NOT_FOUND"));

        ViewCountPost viewCountPost = new ViewCountPost(0L);

        Post post = new Post(
                createPostRequest.getTitle(),
                createPostRequest.getOptionA(),
                createPostRequest.getOptionB(),
                member,
                new Date(),
                viewCountPost
        );
        viewCountPost.setPost(post);

        postRepository.save(post);
    }

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
    public void updatePost(Long postId, UpdatePostRequest updatePostRequest, String username) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new AppException(ErrorCode.POST_NOT_FOUND, "")
        );
        post.setTitle(updatePostRequest.getTitle());
        post.setOptionA(updatePostRequest.getOptionA());
        post.setOptionB(updatePostRequest.getOptionB());

        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId, String username) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND, ""));

        postRepository.delete(post);
    }


    @Override
    public void likePost(Long postId, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, ""));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND, ""));

        LikePost likePost = new LikePost(post, member);

        likePostRepository.save(likePost);
    }
}