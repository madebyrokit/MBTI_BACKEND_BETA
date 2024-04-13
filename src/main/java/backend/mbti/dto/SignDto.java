package backend.mbti.dto;

import lombok.*;

public class SignDto {
    @Data
    static class LoginRequest {
        private String email;
        private String password;
    }
    @Data
    static class SignUpRequest {
        private String email;
        private String password;
        private String username;
        private String mbtitype;
    }

    @Data
    static class JwtResponse {
        private String token;
    }
}
