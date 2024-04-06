package backend.mbti.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;
}
