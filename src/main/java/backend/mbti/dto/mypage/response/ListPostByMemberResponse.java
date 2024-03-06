package backend.mbti.dto.mypage.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListPostByMemberResponse {
    private Long postId;
    private String title;
}
