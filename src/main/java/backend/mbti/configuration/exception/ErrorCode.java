package backend.mbti.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "Duplicated User Name"),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "Invalid User Name"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Invalid Post"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "User Or Password is invalid"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Invalid Comment");

    private final HttpStatus httpStatus;
    private final String message;
}
