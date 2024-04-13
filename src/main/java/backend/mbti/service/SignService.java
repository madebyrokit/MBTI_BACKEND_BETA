package backend.mbti.service;

public interface SignService {
    String login(LoginRequest loginRequest);
    void signup(SignUpRequest signUpRequest);
}
