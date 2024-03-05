package backend.mbti.post;


import backend.mbti.dto.post.PostCreateRequest;
import backend.mbti.dto.post.PostResponse;
import backend.mbti.dto.post.PostUpdateRequest;
import backend.mbti.domain.post.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post createPost(PostCreateRequest request, String username);
    List<PostResponse> viewPostList();
    PostResponse viewPost(Long postId);
    Post updatePost(Long postId, PostUpdateRequest request, String username);
    void deletePost(Long postId, String username);
    void likePost(Long postId, String username);
}