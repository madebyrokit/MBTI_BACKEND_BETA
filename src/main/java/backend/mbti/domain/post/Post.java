package backend.mbti.domain.post;

import backend.mbti.domain.comment.Comment;
import backend.mbti.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String optionA;

    @Column
    private String optionB;

    @ManyToOne
    private Member member;

    @Column
    private Date createdAt;

    @OneToOne(cascade = CascadeType.ALL) // 조회수
    private ViewCountPost viewCountPost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) // 좋아요
    private List<LikePost> likePost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    public Post(String title, String optionA, String optionB, Member member, Date createdAt, ViewCountPost viewCountPost) {
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.member = member;
        this.createdAt = createdAt;
        this.viewCountPost = viewCountPost;
    }
}
