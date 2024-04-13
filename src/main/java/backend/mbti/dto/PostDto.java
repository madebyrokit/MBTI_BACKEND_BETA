package backend.mbti.dto;

import lombok.*;
import net.bytebuddy.jar.asm.commons.Remapper;

import java.util.Date;

public class PostDto {
    @Data
    public static class CreateRequest{
        private String title;
        private String optionA;
        private String optionB;
    }
    @Data
    public static class CreateResponse {
        private Long id;
        private String title;
        private String optionA;
        private String optionB;
        private Long view;
        private Long like;

    }

    @Data
    public static class Response {
        private Long id;
        private String title;
        private String optionA;
        private String optionB;
        private Long view;
        private Long like;
    }
    @Data
    @AllArgsConstructor
    public static class ListResponse {
        private Long id;
        private String title;
        private String optionA;
        private String optionB;
        private Date createAt;
        private Long view;
        private Long like;
    }
    @Data
    public static class DeleteRequest {
        private Long postId;
    }
    @Data
    public static class UpdateRequest {
        private Long postId;
        private String title;
        private String optionA;
        private String optionB;
    }
}
