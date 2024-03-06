package backend.mbti.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "중복된 아아디"),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 없음"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없음" ),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없음");

    private HttpStatus httpStatus;
    private String message;
}
