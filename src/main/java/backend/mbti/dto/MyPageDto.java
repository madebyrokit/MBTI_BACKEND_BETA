package backend.mbti.dto;

import backend.mbti.domain.ProfileImage;
import lombok.*;

public class MyPageDto {
    @Data
    public static class Member {
        private Long id;
        private String email;
        private String password;
        private ProfileImage profileImage;
        private String userName;
        private String mbtiType;
        private String oAuth;
    }
    @Data
    public static class UpdateMember {
        private String username;
        private String currentPassword;
        private String newPassword;
        private String mbtitype;
    }
    @Data
    public static class ListPostResponse {
        private Long postId;
        private String title;
    }
}