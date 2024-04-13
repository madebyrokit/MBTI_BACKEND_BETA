package backend.mbti.dto;

import backend.mbti.domain.ProfileImage;
import lombok.*;

public class MyPageDto {
    @Data
    static class Member {
        private Long id;
        private String email;
        private String password;
        private ProfileImage profileImage;
        private String userName;
        private String mbtiType;
        private String oAuth;
    }
    @Data
    static class UpdateMember {
        private String username;
        private String currentPassword;
        private String newPassword;
        private String mbtitype;
    }
    @Data
    static class ListPostResponse {
        private Long postId;
        private String title;
    }
}