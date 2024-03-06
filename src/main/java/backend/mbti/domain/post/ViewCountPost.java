package backend.mbti.domain.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ViewCountPost {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long view;

    @OneToOne
    private Post post;

    public void incrementView() {
        this.view++;
    }

    public ViewCountPost(Long view) {
        this.view = view;
    }
}
