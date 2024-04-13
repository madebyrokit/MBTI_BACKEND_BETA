package backend.mbti.dto;

import lombok.Data;

public class LikePostDto {
    @Data
    static class LikeRequest {
        private Long postId;
        private Long memberId;
    }
}
