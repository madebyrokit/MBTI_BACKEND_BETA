package backend.mbti.sign;

import backend.mbti.dto.sign.LoginRequest;
import backend.mbti.dto.sign.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "Authorization")
public class SignController {
    private final SignService signService;

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = signService.login(loginRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> sign(@RequestBody SignUpRequest signUpRequest) {
        String signup = signService.signup(signUpRequest);
        return ResponseEntity.status(HttpStatus.OK).body("username: " + signup);
    }
}
