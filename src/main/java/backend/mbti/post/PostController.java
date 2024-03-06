package backend.mbti.post;

import backend.mbti.dto.post.CreatePostRequest;
import backend.mbti.dto.post.PostResponse;
import backend.mbti.dto.post.UpdatePostRequest;

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
    public void createPost(@RequestBody CreatePostRequest createPostRequest, Authentication authentication) {
        postService.createPost(createPostRequest, authentication.getName());
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> viewPostList() {
        return ResponseEntity.ok().body(postService.viewPostList());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> viewPost(@PathVariable Long postId) {
        PostResponse postResponse = postService.viewPost(postId);
        if (postResponse != null) {
            return ResponseEntity.ok(postResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest, Authentication authentication) {
        postService.updatePost(postId, updatePostRequest, authentication.getName());
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, Authentication authentication) {
        postService.deletePost(postId, authentication.getName());
    }

    @PostMapping("/{postId}/like")
    public void toggleLike(@PathVariable Long postId, Authentication authentication) {
        postService.likePost(postId, authentication.getName());
    }
}