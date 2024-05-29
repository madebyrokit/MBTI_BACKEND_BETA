package backend.mbti.controller;

import backend.mbti.dto.PostDto;

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
    public ResponseEntity<PostDto.Response> viewPost(@PathVariable Long postId) {
        PostDto.Response postResponse = postService.getPost(postId);
        if (postResponse != null) {
            return ResponseEntity.ok(postResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public void updatePost(@RequestBody PostDto.UpdateRequest updatePostRequest, Authentication authentication) {
        postService.updatePost(updatePostRequest, authentication.getName());
    }

    @DeleteMapping
    public void deletePost(@RequestBody PostDto.DeleteRequest deletePostRequest, Authentication authentication) {
        postService.deletePost(deletePostRequest, authentication.getName());
    }

    @PostMapping("/like")
    public ResponseEntity<String> toggleLike(@RequestBody PostDto.LikePostRequest likePostRequest, Authentication authentication) {
        return ResponseEntity.ok().body(postService.likePost(likePostRequest, authentication.getName()));
    }
}