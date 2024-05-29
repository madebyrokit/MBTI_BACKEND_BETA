//package backend.mbti.controller;
//
//import backend.mbti.dto.mypage.request.UpdateMemberRequest;
//import backend.mbti.dto.mypage.response.ListPostByMemberResponse;
//import backend.mbti.dto.mypage.response.ViewMemberInfoResponse;
//import backend.mbti.service.MypageService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/mypage")
//public class MypageController {
//
//    private final MypageService mypageService;
//
//    @GetMapping
//    public ResponseEntity<ViewMemberInfoResponse> viewUserInfo(Authentication authentication) {
//        return ResponseEntity.ok(mypageService.getUserInfo(authentication.getName()));
//    }
//
//    @PutMapping
//    public void updateMyProfile(@RequestBody UpdateMemberRequest updateMemberRequest, Authentication authentication) {
//        mypageService.updateMember(updateMemberRequest, authentication.getName());
//    }
//
//    @GetMapping("/posts")
//    public ResponseEntity<List<ListPostByMemberResponse>> getPostsByMember(Authentication authentication) {
//        return ResponseEntity.ok(mypageService.getPostsByMember(authentication.getName()));
//    }
//
//    @GetMapping("/comments")
//    public ResponseEntity<Void> listCommentByMember(Authentication authentication) {
//        return ResponseEntity.ok().build();
//    }
//    @GetMapping("/like/post")
//    public ResponseEntity<Void> likePostsByMember(Authentication authentication) {
//        return ResponseEntity.ok().build();
//    }
//    @GetMapping("/like/comment")
//    public ResponseEntity<Void> likeCommentsByMember(Authentication authentication) {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/report")
//    public ResponseEntity<Void> viewReportByMember(Authentication authentication) {
//        return ResponseEntity.ok().build();
//    }
//    @DeleteMapping
//    public void deleteMember(Authentication authentication) {
//        mypageService.deleteMember(authentication.getName());
//    }
//}
