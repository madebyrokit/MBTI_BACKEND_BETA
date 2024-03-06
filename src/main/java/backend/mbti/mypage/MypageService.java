package backend.mbti.mypage;

import backend.mbti.dto.mypage.UpdateMemberRequest;
import backend.mbti.domain.member.Member;
import backend.mbti.domain.post.Post;

import java.util.List;

public interface MypageService {
    Member getUserInfo(String username);
    Member updateAllMemberInfo(UpdateMemberRequest request, String username);
    List<Post> getPostsByMember(String username);
    void deleteMember(String username);
}
