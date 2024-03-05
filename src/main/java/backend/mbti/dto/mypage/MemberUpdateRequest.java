package backend.mbti.dto.mypage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequest {

    private String email;

    private String mbti;

    private String username;

    private String currentPassword;

    private String newPassword;
}