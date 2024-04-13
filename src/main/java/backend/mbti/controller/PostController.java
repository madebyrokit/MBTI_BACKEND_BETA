package backend.mbti.controller;

import backend.mbti.dto.PostDto;
import backend.mbti.dto.post.*;

import backend.mbti.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostDto.CreateRequest createRequest, Authentication authentication) {
        postService.createPost(createRequest, authentication.getName());
    }

    @GetMapping
    public ResponseEntity<List<PostDto.ListResponse>> viewPostList() {
        return ResponseEntity.ok().body(postService.getListByPost());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto.> viewPost(@PathVariable Long postId) {
        PostResponse postResponse = postService.getPost(postId);
        if (postResponse != null) {
            return ResponseEntity.ok(postResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public void updatePost(@RequestBody UpdatePostRequest updatePostRequest, Authentication authentication) {
        postService.updatePost(updatePostRequest, authentication.getName());
    }

    @DeleteMapping
    public void deletePost(@RequestBody DeletePostRequest deletePostRequest, Authentication authentication) {
        postService.deletePost(deletePostRequest, authentication.getName());
    }

    @PostMapping("/like")
    public void toggleLike(LikePostRequest likePostRequest, Authentication authentication) {
        postService.likePost(likePostRequest, authentication.getName());
    }
}