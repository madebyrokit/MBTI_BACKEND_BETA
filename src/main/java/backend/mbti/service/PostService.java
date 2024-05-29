package backend.mbti.service;


import backend.mbti.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    void createPost(PostDto.CreateRequest createRequest, String username);
    List<PostDto.ListResponse> getListByPost();
    PostDto.Response getPost(Long postId);
    void updatePost(PostDto.UpdateRequest updatePostRequest, String username);
    void deletePost(PostDto.DeleteRequest deletePostRequest, String username);
    String likePost(PostDto.LikePostRequest likePostRequest, String username);
}