package backend.mbti.dto;

import lombok.Data;

public class LikePostDto {
    @Data
    public static class LikeRequest {
        private Long postId;
        private Long memberId;
    }
}
