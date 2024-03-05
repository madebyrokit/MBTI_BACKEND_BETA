package backend.mbti.post;



import backend.mbti.dto.post.PostCreateRequest;
import backend.mbti.dto.post.PostResponse;
import backend.mbti.dto.post.PostUpdateRequest;
import backend.mbti.domain.post.Post;

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
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest request, Authentication authentication) {
        String username = authentication.getName();
        Post createdPost = postService.createPost(request, username);
        return ResponseEntity.ok(createdPost);
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
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request, Authentication authentication) {
        String username = authentication.getName();
        Post updatedPost = postService.updatePost(postId, request, username);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        postService.deletePost(postId, username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> toggleLike(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        postService.likePost(postId, username);
        return ResponseEntity.ok("요청 완료");
    }
}