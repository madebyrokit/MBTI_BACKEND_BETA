package backend.mbti.sign;

import backend.mbti.domain.Member;
import backend.mbti.domain.ProfileImage;
import backend.mbti.repository.SignRepository;
import backend.mbti.service.SignServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SignControllerTest {

    @InjectMocks
    private SignServiceImpl signService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private SignRepository signRepository;

    public SignControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void login() {

    }

    @Test
    void sign() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("test@test.com");
        signUpRequest.setPassword("1234");
        signUpRequest.setUsername("test");
        signUpRequest.setMbtitype("mbti");

        String encodedPassword = bCryptPasswordEncoder.encode(signUpRequest.getPassword());

        Member member = new Member(
                signUpRequest.getEmail(),
                encodedPassword,
                signUpRequest.getUsername(),
                signUpRequest.getMbtitype(),
                null
        );
        member.setProfileImage(new ProfileImage("default.jpg", member));

        when(bCryptPasswordEncoder.encode(signUpRequest.getPassword())).thenReturn(encodedPassword);

        signService.signup(signUpRequest);

        verify(signRepository).save(any(Member.class));
    }

}