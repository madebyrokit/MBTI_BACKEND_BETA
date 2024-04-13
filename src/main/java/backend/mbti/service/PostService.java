package backend.mbti.service;


import backend.mbti.dto.PostDto;
import backend.mbti.dto.post.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    void createPost(PostDto.CreateRequest createRequest, String username);
    List<PostDto.ListResponse> getListByPost();
    PostResponse getPost(Long postId);
    void updatePost(UpdatePostRequest updatePostRequest, String username);
    void deletePost(DeletePostRequest deletePostRequest, String username);
    void likePost(LikePostRequest likePostRequest, String username);
}