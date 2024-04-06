package backend.mbti.service;

import backend.mbti.dto.sign.LoginRequest;
import backend.mbti.dto.sign.SignUpRequest;

public interface SignService {
    String login(LoginRequest loginRequest);
    void signup(SignUpRequest signUpRequest);
}
