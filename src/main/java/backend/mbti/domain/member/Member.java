package backend.mbti.domain.member;

import backend.mbti.domain.comment.Comment;
import backend.mbti.domain.comment.CommentLike;
import backend.mbti.domain.post.LikePost;
import backend.mbti.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private ProfileImage profileImage;

    @Column(unique = true)
    private String username;

    @Column
    private String mbtitype;

    @Column
    private String oAuth;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> postList;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<LikePost> likePostList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> commentList;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikeList;

    public Member(String email, String password, String username, String mbtitype, String oAuth) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.mbtitype = mbtitype;
        this.oAuth = oAuth;
    }
}
