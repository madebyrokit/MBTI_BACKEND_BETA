package backend.mbti.oauth;

public interface KakaoService {
    // 카카오 로그인
    String processKakaoCallback(String code);

}
