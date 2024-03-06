package backend.mbti.dto.post;

import lombok.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

@Getter
@Setter
@RequiredArgsConstructor
public class  CreatePostRequest{
    private String title;
    private String optionA;
    private String optionB;
}
