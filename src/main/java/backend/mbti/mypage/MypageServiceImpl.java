package backend.mbti.mypage;

import backend.mbti.dto.mypage.UpdateMemberRequest;
import backend.mbti.domain.member.Member;
import backend.mbti.domain.post.Post;
import backend.mbti.post.PostRepository;
import backend.mbti.sign.SignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final SignRepository signRepository;
    private final PostRepository postRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 파일 경로
    @Value("${file.upload-dir}")
    private String uploadDir;

    // 유저 정보 뷰
    @Override
    public Member getUserInfo(String username) {
        Optional<Member> member = signRepository.findByUsername(username);
        return member.get();
    }

    // 회원 정보 수정
    @Override
    public Member updateAllMemberInfo(UpdateMemberRequest request, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        if (!member.getUsername().equals(username)) {
            throw new AccessDeniedException("올바르지 못한 접근");
        }

        // 업데이트 로직

        member.setMbtitype(request.getMbti());
        member.setEmail(request.getEmail());

        if (request.getCurrentPassword() != null && request.getNewPassword() != null) {
            if (!bCryptPasswordEncoder.matches(request.getCurrentPassword(), member.getPassword())) {
                throw new IllegalArgumentException("비밀번호 틀림!");
            }
            member.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        }

        return signRepository.save(member);
    }




    // 내가 만든 토론
    @Override
    public List<Post> getPostsByMember(String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        return postRepository.findByMemberOrderByCreatedAtDesc(member);
    }

    // 문의하기

    // 회원 탈퇴
    @Override
    public void deleteMember(String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        if (!member.getUsername().equals(username)) {
            throw new AccessDeniedException("잘못된 접근! 회원 탈퇴 불가!");
        }

        signRepository.delete(member);
    }
}
