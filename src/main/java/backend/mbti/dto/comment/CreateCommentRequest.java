package backend.mbti.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private Long postId;
    private String content;
    private String option;
}
