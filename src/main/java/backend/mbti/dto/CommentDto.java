package backend.mbti.dto;

import lombok.*;

public class CommentDto{
    @Data
    static class CreateRequest {
        private Long postId;
        private String content;
        private String option;
    }

    @Data
    static class DeleteRequest {
        private Long commentId;
    }

    @Data
    static class UpdateRequest {
        private Long postId;
        private String content;
        private String option;
    }
}
