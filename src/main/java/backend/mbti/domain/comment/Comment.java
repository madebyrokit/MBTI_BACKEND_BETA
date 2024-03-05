package backend.mbti.domain.comment;

import backend.mbti.domain.member.Member;
import backend.mbti.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private Integer likeCount;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Member member;

    @Column
    private Character selectOption;

    @Column
    private Date createdAt;

    public Comment(String content, Integer likeCount, Post post, Member member, Character selectOption, Date createdAt) {
        this.content = content;
        this.likeCount = likeCount;
        this.post = post;
        this.member = member;
        this.selectOption = selectOption;
        this.createdAt = createdAt;
    }
}
