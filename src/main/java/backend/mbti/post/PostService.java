package backend.mbti.post;


import backend.mbti.dto.post.CreatePostRequest;
import backend.mbti.dto.post.PostResponse;
import backend.mbti.dto.post.UpdatePostRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    void createPost(CreatePostRequest createPostRequest, String username);
    List<PostResponse> viewPostList();
    PostResponse viewPost(Long postId);
    void updatePost(Long postId, UpdatePostRequest request, String username);
    void deletePost(Long postId, String username);
    void likePost(Long postId, String username);
}