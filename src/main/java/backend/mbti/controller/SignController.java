package backend.mbti.controller;

import backend.mbti.dto.SignDto;
import backend.mbti.service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "Authorization")
public class SignController {
    private final SignService signService;

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody SignDto.LoginRequest loginRequest) {
        String token = signService.login(loginRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body(token);
    }

    @PostMapping("/signup")
    public void sign(@RequestBody SignDto.SignUpRequest signUpRequest) {
        signService.signup(signUpRequest);
    }
}
