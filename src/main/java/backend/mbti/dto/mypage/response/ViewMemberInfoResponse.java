package backend.mbti.dto.mypage.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewMemberInfoResponse {
    private String email;
    private String username;
    private String mbtitype;
}
