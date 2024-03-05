package backend.mbti.dto.sign;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {

    private String email;

    private String password;

    private String username;

    private String mbtitype;
}
