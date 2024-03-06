package backend.mbti.mypage;

import backend.mbti.dto.mypage.UpdateMemberRequest;
import backend.mbti.domain.member.Member;
import backend.mbti.domain.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    @GetMapping
    public ResponseEntity<Member> viewUserInfo(Authentication authentication) {
        String username = authentication.getName();

        Member member = mypageService.getUserInfo(username);

        return ResponseEntity.ok(member);
    }

    @PutMapping
    public ResponseEntity<Member> updateAllMemberInfo(@RequestBody UpdateMemberRequest request, Authentication authentication) {
        String username = authentication.getName();
        Member updatedMember = mypageService.updateAllMemberInfo(request, username);

        if (updatedMember != null) {
            return ResponseEntity.ok(updatedMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // 프로필 저장

    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getPostsByMember(@PathVariable String userId, Authentication authentication) {
        String authUserId = authentication.getName();

        if (!userId.equals(authUserId)) {
            throw new AccessDeniedException("접속 불가!");
        }

        List<Post> posts = mypageService.getPostsByMember(userId);
        return ResponseEntity.ok(posts);
    }

    // 문의하기

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMember(Authentication authentication) {
        String username = authentication.getName();
        log.info(username);
        mypageService.deleteMember(username);
        log.info("delete");
        return ResponseEntity.noContent().build();
    }
}
