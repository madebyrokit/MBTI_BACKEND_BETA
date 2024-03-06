package backend.mbti.mypage;

import backend.mbti.domain.post.Post;
import backend.mbti.dto.mypage.request.UpdateMemberRequest;
import backend.mbti.domain.member.Member;
import backend.mbti.dto.mypage.response.ListPostByMemberResponse;
import backend.mbti.dto.mypage.response.ViewMemberInfoResponse;
import backend.mbti.exception.AppException;
import backend.mbti.exception.ErrorCode;
import backend.mbti.post.PostRepository;
import backend.mbti.sign.SignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final SignRepository signRepository;
    private final PostRepository postRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public ViewMemberInfoResponse getUserInfo(String username) {
        Optional<Member> member = signRepository.findByUsername(username);

        return new ViewMemberInfoResponse(
                member.get().getEmail(),
                member.get().getUsername(),
                member.get().getMbtitype()
        );
    }

    @Override
    public void updateMember(UpdateMemberRequest updateMemberRequest, String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        if (!bCryptPasswordEncoder.matches(updateMemberRequest.getCurrentPassword(), member.getPassword())) {
            new AppException(ErrorCode.INVALID_PASSWORD);
        } else {
            member.setPassword(updateMemberRequest.getNewPassword());
            member.setUsername(updateMemberRequest.getUsername());
            member.setMbtitype(updateMemberRequest.getMbtitype());
        }
    }

    @Override
    public List<ListPostByMemberResponse> getPostsByMember(String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));
        List<Post> postList = postRepository.findByMemberOrderByCreatedAtDesc(member);
        List<ListPostByMemberResponse> listPostByMemberResponses = new ArrayList<>();

        for (Post post : postList) {
            listPostByMemberResponses.add(new ListPostByMemberResponse(post.getId(), post.getTitle()));
        }

        return listPostByMemberResponses;
    }

    @Override
    public void deleteMember(String username) {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        signRepository.delete(member);
    }
}
