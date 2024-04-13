package backend.mbti.dto;

import lombok.Data;

public class LikeCommentDto {
    @Data
    public static class LikeRequest {
        private Long commentId;
    }
}
