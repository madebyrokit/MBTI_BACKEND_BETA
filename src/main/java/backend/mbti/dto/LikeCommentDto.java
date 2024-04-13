package backend.mbti.dto;

import lombok.Data;

public class LikeCommentDto {
    @Data
    static class LikeRequest {
        private Long commentId;
    }
}
