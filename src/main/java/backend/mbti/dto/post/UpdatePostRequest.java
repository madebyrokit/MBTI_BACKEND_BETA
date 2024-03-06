package backend.mbti.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePostRequest {
    private String title;
    private String optionA;
    private String optionB;
}
