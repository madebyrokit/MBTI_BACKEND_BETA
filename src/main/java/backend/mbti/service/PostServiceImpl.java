package backend.mbti.service;

import backend.mbti.domain.LikePost;
import backend.mbti.dto.PostDto;

import backend.mbti.domain.Member;
import backend.mbti.domain.Post;
import backend.mbti.configuration.exception.AppException;
import backend.mbti.configuration.exception.ErrorCode;
import backend.mbti.repository.CommentRepository;
import backend.mbti.repository.LikePostRepository;
import backend.mbti.repository.PostRepository;
import backend.mbti.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;
    private final CommentRepository commentRepository;

    @Override
    public void createPost(PostDto.CreateRequest createRequest, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        Post post = new Post(
                null,
                createRequest.getTitle(),
                createRequest.getOptionA(),
                createRequest.getOptionB(),
                member,
                new Date(),
                0L,
                null,
                null
        );

        postRepository.save(post);
    }

    @Override
    public List<PostDto.ListResponse> getListByPost() {
        List<Post> postList = postRepository.findAllByOrderByIdDesc();

        return postList.stream()
                .map(post -> new PostDto.ListResponse(
                        post.getId(),
                        post.getMember().getUsername(),
                        post.getTitle(),
                        post.getOptionA(),
                        post.getOptionB(),
                        post.getCreatedAt(),
                        post.getView(),
                        commentRepository.countCommentByPost(post)
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public PostDto.Response getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        log.info(likePostRepository.countByPostId(postId).toString());
        return new PostDto.Response(
                post.get().getId(),
                post.get().getMember().getUsername(),
                post.get().getTitle(),
                post.get().getOptionA(),
                post.get().getOptionB(),
                post.get().getView(),
                likePostRepository.countByPostId(postId));
    }

    @Override
    public void updatePost(PostDto.UpdateRequest updatePostRequest, String username) {
        Post post = postRepository.findById(updatePostRequest.getPostId()).orElseThrow(()->
                new AppException(ErrorCode.POST_NOT_FOUND)
        );
        post.setTitle(updatePostRequest.getTitle());
        post.setOptionA(updatePostRequest.getOptionA());
        post.setOptionB(updatePostRequest.getOptionB());

        postRepository.save(post);
    }

    @Override
    public void deletePost(PostDto.DeleteRequest deletePostRequest, String username) {
        Post post = postRepository.findById(deletePostRequest.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        postRepository.delete(post);
    }

    @Override
    public String likePost(PostDto.LikePostRequest likePostRequest, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        Post post = postRepository.findById(likePostRequest.getPostId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        LikePost s = likePostRepository.findByMember(member);
        if (s == null) {
            LikePost likePost = new LikePost(post, member);
            likePostRepository.save(likePost);
            post.getLikePost().add(likePost);
            postRepository.save(post);
            return "SAVE OK";
        } else {
            likePostRepository.delete(s);
            return "DELETE OK";
        }

    }
}