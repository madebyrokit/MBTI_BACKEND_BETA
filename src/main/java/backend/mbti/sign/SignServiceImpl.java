package backend.mbti.sign;

import backend.mbti.jwt.JwtProvider;
import backend.mbti.domain.member.ProfileImage;
import backend.mbti.dto.sign.LoginRequest;
import backend.mbti.dto.sign.SignUpRequest;
import backend.mbti.domain.member.Member;
import backend.mbti.exception.AppException;
import backend.mbti.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SignRepository signRepository;

    @Value("${file.upload-dir}")
    private String filePath;

    @Value("${jwt.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60L;

    @Override
    public void signup(SignUpRequest signUpRequest) {
        String encPwd = bCryptPasswordEncoder.encode(signUpRequest.getPassword());

        Member member = new Member(
                signUpRequest.getEmail(),
                encPwd,
                signUpRequest.getUsername(),
                signUpRequest.getMbtitype(),
                null
        );
        member.setProfileImage(new ProfileImage("default.jpg", member));

        signRepository.save(member);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Member member = signRepository.findAllByEmail(loginRequest.getEmail())
                .orElseThrow(() ->new AppException(ErrorCode.USERNAME_NOT_FOUND));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        String token = JwtProvider.createToken(member.getUsername(), key, expireTimeMs);

        return token;
    }

}
