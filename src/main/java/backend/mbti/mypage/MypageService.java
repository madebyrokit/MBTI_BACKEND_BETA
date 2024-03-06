package backend.mbti.mypage;

import backend.mbti.dto.mypage.request.UpdateMemberRequest;
import backend.mbti.domain.post.Post;
import backend.mbti.dto.mypage.response.ListPostByMemberResponse;
import backend.mbti.dto.mypage.response.ViewMemberInfoResponse;

import java.util.List;

public interface MypageService {
    ViewMemberInfoResponse getUserInfo(String username);
    void updateMember(UpdateMemberRequest updateMemberRequest, String username);
    List<ListPostByMemberResponse> getPostsByMember(String username);
    void deleteMember(String username);
}
