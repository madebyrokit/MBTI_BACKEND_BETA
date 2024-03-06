package backend.mbti.dto.mypage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberRequest {
    private String username;
    private String currentPassword;
    private String newPassword;
    private String mbtitype;
}