package backend.mbti.dto;

import lombok.*;

public class CommentDto{
    @Data
    public static class CreateRequest {
        private Long postId;
        private String content;
        private String option;
    }

    @Data
    public static class DeleteRequest {
        private Long commentId;
    }

    @Data
    public static class UpdateRequest {
        private Long postId;
        private String content;
        private String option;
    }
}
